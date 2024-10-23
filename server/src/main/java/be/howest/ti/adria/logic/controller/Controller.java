package be.howest.ti.adria.logic.controller;

import be.howest.ti.adria.logic.domain.MedicalHistoryRecord;
import be.howest.ti.adria.logic.domain.User;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface Controller {

    JsonObject getSubscriptionTypes();
    void updateSubscriptions(int newSubscriptionID, int userID);
    void createUser(User user);
    int getBloodGroupID(String bloodgroup);
    JsonObject getUserInfoByID(int i);
    List<JsonObject> getMedicalHistoryByID(int i);
    int getLastMedicalEntryId(int adriaId);
    JsonObject getMedicalHistoryEntry(int adriaId, int entryId);
    JsonObject getAllColonies();
    void addMetrics(int adriaId, JsonArray data);
    JsonObject getSymptoms();
    JsonObject getDiagnose(JsonArray symptoms);

    /**
     * gets all metrics within a given timeframe
     * @param adriaID
     * @param start
     * @param end
     * @return JsonObject where the key is the date and the value is an object with the averages for a given day
     */
    JsonObject getMetrics(int adriaID, String start, String end);

    int addMedicalHistoryEntry(MedicalHistoryRecord record);
}
