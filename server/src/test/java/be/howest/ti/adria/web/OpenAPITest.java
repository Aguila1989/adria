package be.howest.ti.adria.web;

import be.howest.ti.adria.logic.controller.Controller;
import be.howest.ti.adria.logic.controller.MockController;
import be.howest.ti.adria.logic.data.Repositories;
import be.howest.ti.adria.web.bridge.OpenApiBridge;
import be.howest.ti.adria.web.bridge.RtcBridge;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.json.JsonObject;

@ExtendWith(VertxExtension.class)
@SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert","PMD.AvoidDuplicateLiterals"})
/*
* PMD.JUnitTestsShouldIncludeAssert: VertxExtension style asserts are marked as false positives.
* PMD.AvoidDuplicateLiterals: Should all be part of the spec (e.g., urls and names of req/res body properties, ...)
*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OpenAPITest {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    public static final String MSG_200_EXPECTED = "Expected a 200 OK http code";
    public static final String MSG_201_EXPECTED = "Expected a 201 created http code";
    public static final String MSG_204_EXPECTED = "Expected a 204 no content message";
    private Vertx vertx;
    private WebClient webClient;

    @BeforeAll
    void deploy(final VertxTestContext testContext) {
        Repositories.shutdown();
        vertx = Vertx.vertx();

        WebServer webServer = new WebServer(new OpenApiBridge(new MockController()), new RtcBridge());
        vertx.deployVerticle(
        webServer,
        testContext.succeedingThenComplete()
        );
        webClient = WebClient.create(vertx);
    }

    @AfterAll
    void close(final VertxTestContext testContext) {
        vertx.close(testContext.succeedingThenComplete());
        webClient.close();
        Repositories.shutdown();
    }

    @Test
    void createUserTest(final VertxTestContext testContext) {
        JsonObject res = getUserJson();

        webClient.post(PORT, HOST, "/api/users").sendJsonObject(res)
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.CREATED, response.statusCode(), MSG_201_EXPECTED);
            Assertions.assertNull(response.body());
            testContext.completeNow();
        }));
    }

    private JsonObject getUserJson() {
        JsonObject res = new JsonObject();
        res.put("type", "createUser");

        JsonObject data = new JsonObject();
        data.put("adriaId", 45);
        data.put("subscriptionType", 1);
        data.put("firstname", "Alice");
        data.put("lastname", "Alison");
        data.put("birthdate", "2064-07-18");
        data.put("colonyId", 1);
        data.put("bloodType", "A+");
        data.put("gender", "F");

        res.put("data", data);
        return res;
    }

    @Test
    void updateUserSubscriptionTest(final VertxTestContext testContext) {
        JsonObject objToSend = new JsonObject();
        objToSend.put("type", "changeSubscription");
        JsonObject data = new JsonObject();
        data.put("newSubscriptionType", 3);
        objToSend.put("data", data);

        webClient.put(PORT, HOST, "/api/users/1").sendJsonObject(objToSend)
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.NO_CONTENT, response.statusCode(), MSG_204_EXPECTED);
            Assertions.assertNull(response.body());
            testContext.completeNow();
        }));
    }

    @Test
    void getSubscriptionsTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/subscriptions").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getSubscriptionTypes(), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getUserInfoByIDRequestWithoutMedHistoryImplicitlyTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/users/1").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getUserInfoByID(1), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getUserInfoByIDRequestWithoutMedHistoryExplicitlyTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/users/1?includeMedicalHistory=false").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getUserInfoByID(1), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getUserInfoByIDRequestWithMedHistoryTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        JsonObject expectedResponse = mockController.getUserInfoByID(1);
        expectedResponse.put("medicalHistory", mockController.getMedicalHistoryByID(1));
        webClient.get(PORT, HOST, "/api/users/1?includeMedicalHistory=true").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(expectedResponse, response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getUserMedicalEntryTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/users/1/1").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getMedicalHistoryEntry(1, 1), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getLastUserMedicalEntryTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/users/1/-1").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getMedicalHistoryEntry(1, -1), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getColoniesTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/colonies").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getAllColonies(), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void addMetricTest(final VertxTestContext testContext) {
        JsonObject addMetrics = getAddMetricsJson();

        webClient.post(PORT, HOST, "/api/metrics/1").sendJsonObject(addMetrics)
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.CREATED, response.statusCode(), MSG_201_EXPECTED);
            Assertions.assertNull(response.body());
            testContext.completeNow();
        }));
    }

    private JsonObject getAddMetricsJson() {
        return new JsonObject("{" +
        "  \"type\": \"addMetrics\"," +
        "  \"data\": [" +
        "    {" +
        "      \"metricCategory\": 3," +
        "      \"date\": \"2048-01-01\"," +
        "      \"time\": \"17:00:01\"," +
        "      \"metricValue\": 120" +
        "    }," +
        "    {" +
        "      \"metricCategory\": 3," +
        "      \"date\": \"2048-01-01\"," +
        "      \"time\": \"17:00:05\"," +
        "      \"metricValue\": 117" +
        "    }," +
        "    {" +
        "      \"metricCategory\": 3," +
        "      \"date\": \"2048-01-01\"," +
        "      \"time\": \"17:00:10\"," +
        "      \"metricValue\": 121" +
        "    }," +
        "    {" +
        "      \"metricCategory\": 3," +
        "      \"date\": \"2048-01-01\"," +
        "      \"time\": \"17:00:15\"," +
        "      \"metricValue\": 110" +
        "    }" +
        "  ]" +
        "}");
    }

    @Test
    void getMetricsTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/metrics/1").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getMetrics(1, null, null), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getMetricsWithStartDateTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/metrics/1?startDate=2084-01-05").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getMetrics(1, "2084-01-05", null), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getMetricsWithEndDateTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/metrics/1?endDate=2084-01-05").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getMetrics(1, null, "2084-01-05"), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }

    @Test
    void getMetricsWithStartDateAndEndDateTest(final VertxTestContext testContext) {
        Controller mockController = new MockController();
        webClient.get(PORT, HOST, "/api/metrics/1?startDate=2084-01-03&endDate=2084-01-07").send()
        .onFailure(testContext::failNow)
        .onSuccess(response -> testContext.verify(() -> {
            assertEquals(HttpResponseCode.OK, response.statusCode(), MSG_200_EXPECTED);
            assertEquals(mockController.getMetrics(1, "2084-01-03", "2084-01-07"), response.bodyAsJsonObject());
            testContext.completeNow();
        }));
    }
}