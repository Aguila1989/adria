package be.howest.ti.adria.logic.controller;

import java.util.List;
import java.util.ArrayList;

import be.howest.ti.adria.logic.domain.MedicalHistoryRecord;
import be.howest.ti.adria.logic.domain.User;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
* this controller holds all the expected responses of the controller for the unit tests
*/
public class MockController implements Controller {
    /**
     * returns mock plans
     */
    @Override
    public JsonObject getSubscriptionTypes() {
        JsonObject plans = new JsonObject();
        plans.put("1", "ResQ");
        plans.put("2", "ResQ +");
        plans.put("3", "ResQ Pro");

        JsonObject res = new JsonObject();
        res.put("plans", plans);

        return res;
    }

    @Override
    public void updateSubscriptions(int i, int j) {}

    @Override
    public void createUser(User user) {}

    /**
     * returns mock id for O-
     */
    @Override
    public int getBloodGroupID(String bloodgroup) {
        return 1; //O- (see db-populate script)
    }

    /**
     * returns mock info for adriaId = 1
     */
    @Override
    public JsonObject getUserInfoByID(int adrieId) {
        JsonObject res = new JsonObject("{" +
        "\"adriaID\": 1," +
        "\"firstname\": \"bob\"," +
        "\"lastname\": \"bobbelino\"," +
        "\"birthdate\": \"2004-02-03\"," +
        "\"gender\": \"M\"," +
        "\"subscriptionType\": \"ResQ\"," +
        "\"colony\": \"Adria\"," +
        "\"bloodType\": \"O+\"" +
        "}");
        return res;
    }

    /**
     * returns mock medicalhistory for adriaId = 1
     */
    @Override
    public List<JsonObject> getMedicalHistoryByID(int adriaId) {
        JsonObject entry1 = new JsonObject("{" +
        "    \"entryId\": 1," +
        "    \"date\": \"2084-05-15\"," +
        "    \"time\": \"09:30:00\"," +
        "    \"diagnosedBy\": [" +
        "        \"Emilia Clarke\"," +
        "        \"Natalie Portman (1)\"," +
        "        \"Natalie Portman (2)\"" +
        "    ]," +
        "    \"diagnosis\": {" +
        "        \"condition\": \"Common Cold\"," +
        "        \"symptoms\": [" +
        "            \"mild fever\"" +
        "        ]" +
        "    }" +
        "}");
        JsonObject entry2 = new JsonObject("{" +
        "    \"entryId\": 2," +
        "    \"date\": \"2084-05-16\"," +
        "    \"time\": \"10:00:00\"," +
        "    \"diagnosedBy\": [" +
        "        \"Robert Downey Jr.\"" +
        "    ]," +
        "    \"diagnosis\": {" +
        "        \"condition\": \"Type 2 Diabetes\"," +
        "        \"symptoms\": [" +
        "            \"Dizziness\"" +
        "        ]" +
        "    }" +
        "}");

        List<JsonObject> res = new ArrayList<>();
        res.add(entry1);
        res.add(entry2);

        return res;
    }

    /**
     * returns mock id of last entry for adriaId = 1
     */
    @Override
    public int getLastMedicalEntryId(int adriaId) {
        return 2;
    }

    /**
     * returns mock data for adriaId = 1 en (entryId = 1 of entryId = -1)
     */
    @Override
    public JsonObject getMedicalHistoryEntry(int adriaId, int entryId) {
        if (entryId == 1) {
            return new JsonObject("{" +
            "    \"entryId\": 1," +
            "    \"date\": \"2084-05-15\"," +
            "    \"time\": \"09:30:00\"," +
            "    \"diagnosedBy\": [" +
            "        \"Emilia Clarke\"," +
            "        \"Natalie Portman (1)\"," +
            "        \"Natalie Portman (2)\"" +
            "    ]," +
            "    \"diagnosis\": {" +
            "        \"condition\": \"Common Cold\"," +
            "        \"symptoms\": [" +
            "            \"mild fever\"" +
            "        ]" +
            "    }" +
            "}");
        } else { //-1 test
            return new JsonObject("{" +
            "    \"entryId\": 2," +
            "    \"date\": \"2084-05-16\"," +
            "    \"time\": \"10:00:00\"," +
            "    \"diagnosedBy\": [" +
            "        \"Robert Downey Jr.\"" +
            "    ]," +
            "    \"diagnosis\": {" +
            "        \"condition\": \"Type 2 Diabetes\"," +
            "        \"symptoms\": [" +
            "            \"Dizziness\"" +
            "        ]" +
            "    }" +
            "}");
        }
    }

    /**
     * returns mock colonies
     */
    @Override
    public JsonObject getAllColonies() {
        return new JsonObject("{" +
                "    \"colonies\": {" +
                "        \"1\": \"Adria\"," +
                "        \"2\": \"Mars\"," +
                "        \"3\": \"Remote-1\"," +
                "        \"4\": \"Remote-2\"," +
                "        \"5\": \"Remote-3\"," +
                "        \"6\": \"Remote-4\"" +
                "    }" +
                "}");
    }

    @Override
    public void addMetrics(int adriaId, JsonArray data) {}

    /**
     * <ul>
     *  <li>this method holds the mock data for the following cases:
     *      <ul>
     *      <li>(1, null, null)</li>
     *      <li>(1, "2084-01-05", null)</li>
     *      <li>(1, null, "2084-01-05")</li>
     *      <li>(1, "2084-01-03", "2084-01-07")</li>
     *      </ul>
     *  </li>
     *  <li>warnings:
     *      <ul>
     *          <li>in any other case it returns an empty jsonObject</li>
     *          <li style="color='red'">modifying the db-populate.sql of the tests might break this repo</li>
     *          <li>this controller only keeps working if the db-populate.sql doesn't hold any metrics that are after the date that this function was last modified (+61 years to account for adria time)</li>
     *      </ul>
     *  </li>
     * </ul>
     * <hr>
     */
    @Override
    public JsonObject getMetrics(int adriaID, String start, String end) {
        if (adriaID == 1 && start == null && end == null) {
            return new JsonObject("{\"2084-01-02\":{\"Heart Rate\":72.0,\"Temperature\":37.0},\"2084-01-12\":{\"Heart Rate\":70.0,\"Temperature\":38.0},\"2084-01-07\":{\"Heart Rate\":80.0,\"Temperature\":36.2},\"2084-01-11\":{\"Heart Rate\":71.0,\"Temperature\":39.0},\"2084-01-06\":{\"Heart Rate\":73.0,\"Temperature\":36.1},\"2084-01-01\":{\"Heart Rate\":70.0,\"Temperature\":36.0},\"2084-01-13\":{\"Heart Rate\":68.0,\"Temperature\":36.7},\"2084-01-08\":{\"Heart Rate\":81.0,\"Temperature\":37.5},\"2084-01-03\":{\"Heart Rate\":69.0,\"Temperature\":36.5},\"2084-01-14\":{\"Heart Rate\":66.0,\"Temperature\":36.0},\"2084-01-09\":{\"Heart Rate\":75.0,\"Temperature\":38.0},\"2084-01-04\":{\"Heart Rate\":70.0,\"Temperature\":37.1},\"2084-01-10\":{\"Heart Rate\":76.0,\"Temperature\":38.5},\"2084-01-05\":{\"Heart Rate\":75.0,\"Temperature\":35.9}}");
        } else if (adriaID == 1 && start != null && start.equals("2084-01-05") && end == null) {
            return new JsonObject("{\"2084-01-12\":{\"Heart Rate\":70.0,\"Temperature\":38.0},\"2084-01-07\":{\"Heart Rate\":80.0,\"Temperature\":36.2},\"2084-01-11\":{\"Heart Rate\":71.0,\"Temperature\":39.0},\"2084-01-06\":{\"Heart Rate\":73.0,\"Temperature\":36.1},\"2084-01-14\":{\"Heart Rate\":66.0,\"Temperature\":36.0},\"2084-01-09\":{\"Heart Rate\":75.0,\"Temperature\":38.0},\"2084-01-13\":{\"Heart Rate\":68.0,\"Temperature\":36.7},\"2084-01-08\":{\"Heart Rate\":81.0,\"Temperature\":37.5},\"2084-01-10\":{\"Heart Rate\":76.0,\"Temperature\":38.5},\"2084-01-05\":{\"Heart Rate\":75.0,\"Temperature\":35.9}}");
        } else if (adriaID == 1 && start == null && end != null && end.equals("2084-01-05")) {
            return new JsonObject("{\"2084-01-02\":{\"Heart Rate\":72.0,\"Temperature\":37.0},\"2084-01-01\":{\"Heart Rate\":70.0,\"Temperature\":36.0},\"2084-01-04\":{\"Heart Rate\":70.0,\"Temperature\":37.1},\"2084-01-03\":{\"Heart Rate\":69.0,\"Temperature\":36.5},\"2084-01-05\":{\"Heart Rate\":75.0,\"Temperature\":35.9}}");
        } else if (adriaID == 1 && start != null && end != null && start.equals("2084-01-03") && end.equals("2084-01-07")) {
            return new JsonObject("{\"2084-01-07\":{\"Heart Rate\":80.0,\"Temperature\":36.2},\"2084-01-06\":{\"Heart Rate\":73.0,\"Temperature\":36.1},\"2084-01-04\":{\"Heart Rate\":70.0,\"Temperature\":37.1},\"2084-01-03\":{\"Heart Rate\":69.0,\"Temperature\":36.5},\"2084-01-05\":{\"Heart Rate\":75.0,\"Temperature\":35.9}}");
        }
        return new JsonObject();
    }

    @Override
    public int addMedicalHistoryEntry(MedicalHistoryRecord record) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMedicalHistoryEntry'");
    }

    @Override
    public JsonObject getSymptoms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSymptoms'");
    }

    @Override
    public JsonObject getDiagnose(JsonArray symptoms) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiagnose'");
    }
}
