package be.howest.ti.adria.logic.controller;

import be.howest.ti.adria.logic.data.H2Repository;
import be.howest.ti.adria.logic.data.Repositories;
import be.howest.ti.adria.logic.domain.DiseaseLikelyhood;
import be.howest.ti.adria.logic.domain.DiseaseSymptom;
import be.howest.ti.adria.logic.domain.MedicalHistoryRecord;
import be.howest.ti.adria.logic.domain.Metric;
import be.howest.ti.adria.logic.domain.MetricSummary;
import be.howest.ti.adria.logic.domain.User;
import be.howest.ti.adria.logic.exceptions.RepositoryException;
import be.howest.ti.adria.web.exceptions.IllegalRequestException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import java.util.logging.Level;
import java.util.Map;

/**
* DefaultAdriaController is the default implementation for the AdriaController interface.
* The controller shouldn't even know that it is used in an API context..
*
* This class and all other classes in the logic-package (or future sub-packages)
* should use 100% plain old Java Objects (POJOs). The use of Json, JsonObject or
* Strings that contain encoded/json data should be avoided here.
* Keep libraries and frameworks out of the logic packages as much as possible.
* Do not be afraid to create your own Java classes if needed.
*/
public class DefaultController implements Controller {
    private static final Logger LOGGER = Logger.getLogger(DefaultController.class.getName());

    public JsonObject getSubscriptionTypes() {
        return Repositories.getH2Repo().getSubscriptionTypes();
    }

    public JsonObject getUserInfoByID(int adriaId) {
        return Repositories.getH2Repo().getUserInfoByID(adriaId);
    }

    public List<JsonObject> getMedicalHistoryByID(int adriaId) {
        return Repositories.getH2Repo().getMedicalHistoryById(adriaId);
    }

    @Override
    public void updateSubscriptions(int newSubscriptionID, int userID) {
        Repositories.getH2Repo().updateSubscriptionType(newSubscriptionID, userID);
    }

    @Override
    public void createUser(User user) {
        Repositories.getH2Repo().createUser(user);
    }

    @Override
    public int getBloodGroupID(String bloodgroup) {
        return Repositories.getH2Repo().getBloodGroupIDFromBloodGroup(bloodgroup);
    }

    @Override
    public int getLastMedicalEntryId(int adriaId) {
        return Repositories.getH2Repo().getLastEntryIdForUser(adriaId);
    }

    @Override
    public JsonObject getMedicalHistoryEntry(int adriaId, int entryId) {
        return this.getMedicalHistoryByID(adriaId)
        .stream()
        .filter(entry -> entry.getInteger("entryId") == entryId)
        .findFirst()
        .orElse(null);
    }

    @Override
    public JsonObject getAllColonies() {
        JsonObject res = new JsonObject();
        res.put("colonies", Repositories.getH2Repo().getColonies());
        return res;
    }

    @Override
    public void addMetrics(int adriaId, JsonArray data) {
        List<String> errors = new ArrayList<>();
        H2Repository repo = Repositories.getH2Repo();
        data.stream()
        .map(json ->  {
            try {
                return new Metric(adriaId, (JsonObject) json);
            } catch (IllegalArgumentException ex) {
                errors.add(ex.getMessage());
                return null;
            }
        })
        .forEach(metric -> {
            if (metric != null) {
                try {
                    repo.addMetric(metric);
                } catch (RepositoryException ex) {
                    errors.add(ex.getMessage());
                }
            }
        });

        if (!errors.isEmpty()) {
            throw new IllegalRequestException(errors);
        }
    }

    @Override
    public JsonObject getMetrics(int adriaID, String startDate, String endDate) {
        Date parsedStartDate;
        Date parsedEndDate;
        try { // try parsing dates if not null else use defaults
            parsedStartDate = startDate == null ? Date.valueOf("1970-01-01") : Date.valueOf(startDate);
            parsedEndDate = endDate == null ? getCurrentDate() : Date.valueOf(endDate);
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, "failed parsing dates", ex);
            throw new IllegalRequestException("failed parsing dates from url parameters");
        }

        if (parsedEndDate.before(parsedStartDate)) {
            throw new IllegalRequestException("end date may not be before start date");
        }

        List<Metric> metrics = Repositories.getH2Repo().getMetrics(adriaID, parsedStartDate, parsedEndDate);
        return new MetricSummary(metrics).toJson();
    }

    @Override
    public int addMedicalHistoryEntry(MedicalHistoryRecord record) {
        Repositories.getH2Repo().addMedicalHistoryRecord(record).getEntryId();
        return this.getLastMedicalEntryId(record.getAdriaId());
    }

    /**
    * returns the date as if we are on adria (school year 2023-2024 => 2084-2085 on Adria)
    * @return SQL date 61 years in the future
    */
    public Date getCurrentDate() {
        //forward current year & take 29 feb into account
        LocalDate localDate = LocalDate.now();
        if (!Year.isLeap((long) localDate.getYear() + 61) && localDate.getDayOfMonth() == 29 && localDate.getMonth() == Month.FEBRUARY) {
            localDate = localDate.minusDays(1);
        }
        localDate = localDate.plusYears(61);

        //format the date
        DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String newDate = sqlFormat.format(localDate);

        return Date.valueOf(newDate);
    }

    @Override
    public JsonObject getSymptoms() {
        List<String> symptoms = Repositories.getH2Repo().getSymptoms();
        return new JsonObject().put("symptoms", symptoms);
    }

    @Override
    public JsonObject getDiagnose(JsonArray symptoms) {
        Map<String, List<DiseaseSymptom>> illnessesAndSymptoms = Repositories.getH2Repo().getIllnessesWithSymptoms();
        List<DiseaseLikelyhood> diseasedWithLikelyHood = new ArrayList<>();

        illnessesAndSymptoms.keySet().stream().forEach(illness ->
            diseasedWithLikelyHood.add(new DiseaseLikelyhood(getMatchPercentage(illnessesAndSymptoms.get(illness), symptoms), illness))
        );

        List<DiseaseLikelyhood> top5 = diseasedWithLikelyHood.stream().sorted().limit(5).filter(entry -> entry.getLikelyhood() != 0).toList();

        JsonObject diagnose = new JsonObject();
        top5.forEach(elem ->
        diagnose.put(elem.getIllness(), elem.getLikelyhood())
        );

        JsonObject res = new JsonObject().put("diagnose", diagnose);
        if (!top5.isEmpty()) {
            res.put("mostLikely", top5.get(0).getIllness());
        }

        return res;
    }

    private int getMatchPercentage(List<DiseaseSymptom> symptoms, JsonArray symptomsToMatch) {
        int matches = 0;
        for (int i = 0; i < symptoms.size(); i++) {
            if (symptomsToMatch != null && symptomsToMatch.contains(symptoms.get(i).getSymptom())) {
                matches++;
            }
        }
        int matchPercentage = (int) (((double) matches / symptoms.size()) * 100);
        return Math.min(100, matchPercentage);
    }
}