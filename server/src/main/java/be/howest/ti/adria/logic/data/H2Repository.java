package be.howest.ti.adria.logic.data;

import be.howest.ti.adria.logic.domain.DiseaseSymptom;
import be.howest.ti.adria.logic.domain.Doctor;
import be.howest.ti.adria.logic.domain.MedicalHistoryRecord;
import be.howest.ti.adria.logic.domain.Metric;
import be.howest.ti.adria.logic.domain.User;
import be.howest.ti.adria.logic.exceptions.RepositoryException;
import io.vertx.core.json.JsonObject;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;


/*
This is only a starter class to use an H2 database.
In this start project there was no need for a Java interface AdriaRepository.
Please always use interfaces when needed.

To make this class useful, please complete it with the topics seen in the module OOA & SD
*/

public class H2Repository {
    private static final String ADD_METRIC_QUERY = "INSERT into metrics(USERID, METRICCATEGORYID, DATEOFRECORDING, TIMEOFRECORDING, METRICVALUE) values (?,?,?,?,?)";
    private static final String GET_USER_INFO_QUERY = "SELECT u.id  as adriaid, fname as firstname, lname as lastname, birthdate, gender, st.subscriptionname as subscription, c.name as colony, (b.bloodtype || b.resusfactor) as bloodtype FROM USERS u JOIN subscriptions s ON s.userid = u.id JOIN subscriptiontypes st ON st.id = s.subscriptiontypeid JOIN colonies c ON c.id = u.colonyid JOIN bloodtypes b ON b.id = u.bloodtypeid WHERE u.id = ?";
    private static final String INSERT_NEW_USER_QUERY = "INSERT INTO users (id, fname, lname, birthdate, colonyid, bloodtypeid) values (?,?,?,?,?,?)";
    private static final String UPDATE_SUBSCRIPTION_QUERY = "UPDATE SUBSCRIPTIONS SET SUBSCRIPTIONTYPEID = ? WHERE USERID = ?";
    private static final String GET_MEDICAL_HISTORY_QUERY = "SELECT *, rownum() AS relativeEntryNum FROM medicalhistories WHERE userId LIKE ?";
    private static final String GET_ALL_SUBSCRIPTION_TYPES_QUERY = "SELECT * FROM SUBSCRIPTIONTYPES";
    private static final String GET_BLOODTYPE_ID_QUERY = "SELECT ID FROM BLOODTYPES WHERE BLOODTYPE=? AND RESUSFACTOR=?";
    private static final String GET_DIAGNOSED_BY_IDS_QUERY = "SELECT * FROM diagnosedBy WHERE medicalHistoryId LIKE ?";
    private static final String GET_SYMPTOMS_QUERY = "SELECT * FROM patientSymptoms WHERE medicalhistoryid LIKE ?";
    private static final String GET_ALL_COLONIES_QUERY = "SELECT * FROM colonies";
    private static final String GET_METRICS_QUERRY ="SELECT * FROM metrics JOIN metriccategories mc on mc.id = metriccategoryid WHERE userid = ? AND dateofrecording >= ? AND dateofrecording <= ?";
    private static final String GET_LAST_MEDICAL_HISTORY_ID = "SELECT COUNT(*) as count FROM medicalhistories WHERE userId LIKE ?";

    private static final Logger LOGGER = Logger.getLogger(H2Repository.class.getName());
    private final Server dbWebConsole;
    private final String username;
    private final String password;
    private final String url;

    public H2Repository(String url, String username, String password, int console) {
        try {
            this.username = username;
            this.password = password;
            this.url = url;
            this.dbWebConsole = Server.createWebServer(
            "-ifNotExists",
            "-webPort", String.valueOf(console)).start();
            LOGGER.log(Level.INFO, "Database web console started on port: {0}", console);
            this.generateData();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "DB configuration failed", ex);
            throw new RepositoryException("Could not configure AdriaH2repository");
        }
    }

    public int getLastEntryIdForUser(int adriaId) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_LAST_MEDICAL_HISTORY_ID);
        ) {
            stmt.setInt(1, adriaId);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "failed getting last entryId", e);
            throw new RepositoryException("failed getting last entryId");
        }
    }

    public void addMetric(Metric metric) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(ADD_METRIC_QUERY);
        ) {
            stmt.setInt(1, metric.getAdriaId());
            stmt.setInt(2, metric.getMetricCategory());
            stmt.setDate(3, metric.getDate());
            stmt.setString(4, metric.getTime() == null ? "NULL" : metric.getTime().toString());
            stmt.setDouble(5, metric.getMetricValue());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            String msg = String.format("Failed adding metric %s (probable cause: duplicate metric)", metric);
            LOGGER.log(Level.SEVERE, msg, ex);
            throw new RepositoryException(msg);
        }
    }

    public List<Metric> getMetrics(int adriaId, Date start, Date end) {
        List<Metric> metrics = new ArrayList<>();
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_METRICS_QUERRY)
        ) {
            stmt.setInt(1, adriaId);
            stmt.setString(2, start.toString());
            stmt.setString(3, end.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Metric baseMetric = rs2metric(rs);

                    //kept this seprate so rs2metric can be used in other functions where the querry doesn't (need to) join the name and unit
                    String metricCategoryName = rs.getString("metricname");
                    String unit = rs.getString("unit");
                    baseMetric.setCategoryName(metricCategoryName);
                    baseMetric.setCategoryUnit(unit);

                    metrics.add(baseMetric);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed getting metrics", ex);
            throw new RepositoryException("failed getting metrics");
        }
        return metrics;
    }

    private Metric rs2metric(ResultSet rs) throws SQLException {
        int adriaId = rs.getInt("userid");
        int metricCategory = rs.getInt("metriccategoryid");
        Date date = rs.getDate("dateofrecording");
        String time = rs.getString("timeofrecording");
        double metricValue = rs.getDouble("metricvalue");
        if (time.equals("NULL")) {
            return new Metric(adriaId, metricCategory, metricValue, date);
        } else {
            try {
                Time parsedTime = Time.valueOf(time);
                return new Metric(adriaId, metricCategory, metricValue, date, parsedTime);
            } catch (IllegalArgumentException ex) {
                LOGGER.log(Level.SEVERE, String.format("failed parsing time %s of a metric, potential db integrity problems", time), ex);
                throw new RepositoryException(String.format("encountered a metric with an invalid time (%s)", time));
            }
        }
    }

    public JsonObject getColonies() {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_COLONIES_QUERY);
        ResultSet rs = stmt.executeQuery()) {
            JsonObject res = new JsonObject();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                res.put(id, name);
            }
            return res;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed hetting colonies", ex);
            throw new RepositoryException("failed hetting colonies");
        }
    }

    public JsonObject getUserInfoByID(int adriaId) {
        JsonObject user = new JsonObject();

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_USER_INFO_QUERY);
        ) {
            stmt.setString(1, Integer.toString(adriaId));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user.put("adriaID", adriaId);
            rs2basicUserInfo(user, rs); //relies on objects being reference based
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to get user information, user not found", ex);
            throw new RepositoryException("Failed to get user information, user not found");
        }

        return user;
    }

    public List<JsonObject> getMedicalHistoryById(int adriaId) {
        List<JsonObject> medicalHistory = new ArrayList<>();

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_MEDICAL_HISTORY_QUERY);
        ) {
            stmt.setString(1, Integer.toString(adriaId));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicalHistory.add(rs2historyEntry(rs));
            }
        } catch(SQLException exception) {
            LOGGER.log(Level.SEVERE, "Failed to fetch user medical history.", exception);
            throw new RepositoryException("Could not fetch user medical history.");
        }

        return medicalHistory;
    }

    public MedicalHistoryRecord addMedicalHistoryRecord(MedicalHistoryRecord record) {
        //diagnosedBy & Symptoms
        String query = "INSERT INTO medicalHistories (userid, dateofrecording, timeofrecording, condition) VALUES (?, ?, ?, ?);";
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, record.getAdriaId());
            stmt.setDate(2, record.getDate());
            stmt.setTime(3, record.getTime());
            stmt.setString(4, record.getDiagnosis().getCondition());

            stmt.executeUpdate();

            int entryId;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                entryId = rs.getInt(1);
                record.setEntryId(entryId);
            }

            try {
                addMedicalHistoryRecordDiagnosedBy(record);
                addMedicalHistoryRecordSymptoms(record);
            } catch (RepositoryException ex) {
                addMedicalHistoryRecordCleanup(entryId);
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                throw new RepositoryException(ex.getMessage()); //sonarlint can't handle the fact that i rethrow with the same message
            }

            return record;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed inserting medicalRecord", ex);
            throw new RepositoryException("failed inserting medicalRecord");
        }

    }

    private void addMedicalHistoryRecordCleanup(int entryId) {
        //function will be added if we have time left
    }

    private void addMedicalHistoryRecordSymptoms(MedicalHistoryRecord record) {
        String querry = "INSERT into patientSymptoms(medicalhistoryid, symptom) values (?,?);";
        Arrays.stream(record.getDiagnosis().getSymptoms()).forEach(symptom -> {
            try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(querry)
            ) {
                stmt.setInt(1, record.getEntryId());
                stmt.setString(2, symptom);

                stmt.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "failed adding one or more symptom", ex);
                throw new RepositoryException("failed adding one or more symptom");
            }
        });
    }

    private void addMedicalHistoryRecordDiagnosedBy(MedicalHistoryRecord record) {
        String query = "INSERT INTO diagnosedBy (medicalHistoryId, doctorId) VALUES (?, ?);";
        Arrays.stream(record.getDiagnosedBy()).forEach(doctorId -> {
            try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, record.getEntryId());
                stmt.setInt(2, doctorId);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "failed adding diagnosedBy to db", ex);
                throw new RepositoryException("failed adding one or more diagnosedBy (probable cause: doctor does not exist)");
            }
        });
    }

    public JsonObject getSubscriptionTypes() {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_SUBSCRIPTION_TYPES_QUERY)) {

            ResultSet rs = stmt.executeQuery();
            JsonObject jsonObject = new JsonObject();
            JsonObject plans = new JsonObject();

            while (rs.next()) {
                plans.put(String.valueOf(rs.getInt("ID")), rs.getString("SUBSCRIPTIONNAME"));
            }

            jsonObject.put("plans", plans);
            return jsonObject;

        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, "Failed to fetch subscription types.", exception);
            throw new RepositoryException("Could not fetch subscription types.");
        }
    }

    public void updateSubscriptionType(int updateSubID, int userID) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_SUBSCRIPTION_QUERY)) {

            stmt.setInt(1, updateSubID);
            stmt.setInt(2, userID);

            stmt.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, "Failed to update subscription.", exception);
            throw new RepositoryException("Could not update subscription.");
        }
    }

    public int getBloodGroupIDFromBloodGroup(String fullBloodGroup) {
        boolean isThreeLetters = fullBloodGroup.length() == 3;
        String bloodGroup = isThreeLetters ? fullBloodGroup.substring(0,2) : fullBloodGroup.substring(0,1);
        String resusFactor = isThreeLetters ? fullBloodGroup.substring(2,3) : fullBloodGroup.substring(1,2);

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_BLOODTYPE_ID_QUERY)) {

            stmt.setString(1,bloodGroup);
            stmt.setString(2,resusFactor);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return -1;
            return rs.getInt("ID");

        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, "BloodType fetching failed.", exception);
            throw new RepositoryException("BloodType fetching failed.");
        }
    }

    public void createUser(User user) {
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT_NEW_USER_QUERY)) {

            stmt.setInt(1, (int) user.getAdriaID());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setDate(4, user.getBirthDate());
            stmt.setInt(5, user.getColonyID());
            stmt.setInt(6, user.getBloodTypeID());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to create user.", ex);
            throw new RepositoryException("Could not create user (most likely duplicate adriaId)");
        }

    }

    public Map<String, List<DiseaseSymptom>> getIllnessesWithSymptoms() {
        List<DiseaseSymptom> diseaseSymptoms = new ArrayList<>();
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("select d.name as disease, s.name as symptom from diseases d join disease_symptoms sd on sd.disease_id = d.id join symptoms s on s.id = sd.symptom_id")
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    diseaseSymptoms.add(rs2deseaseSymptom(rs));
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed getting illnesses and diagnosis", ex);
            throw new RepositoryException("failed getting illnesses and diagnosis");
        }

        return diseaseSymptoms.stream().collect(Collectors.groupingBy(DiseaseSymptom::getDisease));
    }

    public List<String> getSymptoms() {
        List<String> res = new ArrayList<>();
        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("select name from symptoms")
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    res.add(rs.getString("name"));
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed getting symptoms", ex);
            throw new RepositoryException("failed getting symptoms");
        }
        return res;
    }

    private DiseaseSymptom rs2deseaseSymptom(ResultSet rs) throws SQLException {
        String disease = rs.getString("disease");
        String symptom = rs.getString("symptom");
        return new DiseaseSymptom(disease, symptom);
    }

    //helper functions getUserInfoByID
    private void rs2basicUserInfo(JsonObject user, ResultSet rs) throws SQLException {
        user.put("firstname", rs.getString("firstname"));
        user.put("lastname", rs.getString("lastname"));
        user.put("birthdate", rs.getDate("birthdate").toLocalDate().toString());
        user.put("gender", rs.getString("gender"));
        user.put("subscriptionType", rs.getString("subscription"));
        user.put("colony", rs.getString("colony"));
        user.put("bloodType", rs.getString("bloodtype"));
    }

    //helper functions getMedicalHistoryById
    private JsonObject rs2historyEntry(ResultSet rs) throws SQLException {
        JsonObject entry = new JsonObject();
        int entryId = rs.getInt("entryId");

        entry.put("entryId", rs.getInt("relativeEntryNum"));
        entry.put("date", rs.getDate("dateofrecording").toLocalDate().toString());
        entry.put("time", rs.getTime("timeofrecording"));

        // diagnosedBy
        List<Integer> doctorIds = getDoctorIdsForEntry(entryId);
        List<String> doctorNames = getDoctorNamesFromIds(doctorIds);
        entry.put("diagnosedBy", doctorNames);

        // diagnosis
        JsonObject diagnosis = new JsonObject();
        diagnosis.put("condition", rs.getString("condition"));
        diagnosis.put("symptoms", getEntrySymptoms(entryId));
        entry.put("diagnosis", diagnosis);

        return entry;
    }

    private List<Integer> getDoctorIdsForEntry(int entryId) {
        List<Integer> doctorIds = new ArrayList<>();

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_DIAGNOSED_BY_IDS_QUERY)
        ) {
            stmt.setString(1, Integer.toString(entryId));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doctorIds.add(rs.getInt("doctorId"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed loading doctorIds", ex);
            throw new RepositoryException("failed loading doctorIds");
        }

        return doctorIds;
    }

    private List<String> getDoctorNamesFromIds(List<Integer> doctorIds) {
        String questionMarks = String.join(",", Stream.generate(() -> "?").limit(doctorIds.size()).toArray(String[]::new));
        String getDoctorsAndNamesakeQuery = "select * FROM doctors d1 WHERE LOWER(d1.fullname) in (SELECT LOWER(d2.fullname) FROM DOCTORS d2 WHERE d2.adriaid in (" + questionMarks + "))";
        List<Doctor> doctorsAndNamesake = new ArrayList<>();

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(getDoctorsAndNamesakeQuery);
        ) {
            //fill the id's in the like clause
            for (int i = 0; i < doctorIds.size(); i++) {
                int doctorId = doctorIds.get(i);
                stmt.setInt(i + 1, doctorId);
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                doctorsAndNamesake.add(rs2doctor(rs));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "failed to get doctorNames", ex);
            throw new RepositoryException("failed to get doctorNames");
        }

        List<String> doctorNames = new ArrayList<>();

        //3 streams
        Map<String, List<Doctor>> groupedByName = doctorsAndNamesake.stream().collect(Collectors.groupingBy(Doctor::getName));
        groupedByName.keySet().stream().forEach(name -> {
            if (groupedByName.get(name).size() == 1) { //no namesake
                doctorNames.add(name);
            } else { //namesakes
                IntStream.range(0, groupedByName.get(name).size())
                .forEach(index -> {
                    Doctor doctor = groupedByName.get(name).get(index);
                    if (doctorIds.contains(doctor.getAdriaId())) { //if its one of the docters for which we want the name
                        doctorNames.add(String.format("%s (%d)", doctor.getName(), index + 1));
                    }
                });
            }
        });

        Collections.sort(doctorNames);

        return doctorNames;
    }

    private Doctor rs2doctor(ResultSet rs) throws SQLException {
        String name = rs.getString("fullname");
        int id = rs.getInt("adriaid");
        return new Doctor(id, name);
    }

    private List<String> getEntrySymptoms(int entryId) {
        List<String> res = new ArrayList<>();

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_SYMPTOMS_QUERY)
        ) {
            stmt.setString(1, Integer.toString(entryId));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                res.add(rs.getString("symptom"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed getting symptoms", ex);
            throw new RepositoryException("Failed getting symptoms");
        }

        return res;
    }

    //repo functions
    public void cleanUp() {
        if (dbWebConsole != null && dbWebConsole.isRunning(false)) {
            dbWebConsole.stop();
        }

        try {
            Files.deleteIfExists(Path.of("./db-05.mv.db"));
            Files.deleteIfExists(Path.of("./db-05.trace.db"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Database cleanup failed.", e);
            throw new RepositoryException("Database cleanup failed.");
        }
    }

    public void generateData() {
        try {
            executeScript("db-create.sql");
            executeScript("db-populate.sql");
        } catch (IOException | SQLException ex) {
            LOGGER.log(Level.SEVERE, "Execution of database scripts failed.", ex);
        }
    }

    private void executeScript(String fileName) throws IOException, SQLException {
        String createDbSql = readFile(fileName);
        try (
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(createDbSql)
        ) {
            stmt.executeUpdate();
        }
    }

    private String readFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RepositoryException("Could not read file: " + fileName);
        }

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
