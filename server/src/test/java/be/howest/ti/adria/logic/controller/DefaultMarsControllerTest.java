package be.howest.ti.adria.logic.controller;

import be.howest.ti.adria.logic.data.Repositories;
import be.howest.ti.adria.logic.domain.User;
import be.howest.ti.adria.logic.domain.enums.Gender;
import be.howest.ti.adria.web.exceptions.IllegalRequestException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultMarsControllerTest {
    
    private static final String URL = "jdbc:h2:./db-05";
    private static final Controller controller = new DefaultController();
    private static final MockController mockController = new MockController();
    
    @BeforeAll
    void setupTestSuite() {
        Repositories.shutdown();
        JsonObject dbProperties = new JsonObject(Map.of("url", URL,
        "username", "",
        "password", "",
        "webconsole.port", 9000));
        Repositories.configure(dbProperties);
    }
    
    @BeforeEach
    void setupTest() {
        Repositories.getH2Repo().generateData();
    }
    
    @Test
    void getSubscriptionTypesTest() {        
        assertEquals(mockController.getSubscriptionTypes() , controller.getSubscriptionTypes());
    }
    
    @Test
    void getBloodGroupIDTest() {        
        assertEquals(1, controller.getBloodGroupID("O-"));
    }
    
    @Test
    void getUserInfoByIDTest() {        
        assertEquals(mockController.getUserInfoByID(1).toString(), controller.getUserInfoByID(1).toString());
    }
    
    @Test
    void getMedicalHistoryByIDTest() {        
        assertEquals(mockController.getMedicalHistoryByID(1).toString(), controller.getMedicalHistoryByID(1).toString());
    }
    
    @Test
    void getLastMedicalEntryIdTest() {
        assertEquals(mockController.getLastMedicalEntryId(1), controller.getLastMedicalEntryId(1));
    }
    
    @Test
    void getMedicalHistoryEntryTest() {
        assertEquals(mockController.getMedicalHistoryEntry(1, 1).toString(), controller.getMedicalHistoryEntry(1, 1).toString());
        assertNull(controller.getMedicalHistoryEntry(1, -1));
    }
    
    @Test
    void updateSubscriptionsTest() {
        assertDoesNotThrow(() -> {
            controller.updateSubscriptions(2, 1);
        }); 
    }
    
    @Test
    void createUserTest() {
        User user = new User(45, "fname", "lanme", new Date(1), Gender.MALE, 1, 1);
        assertDoesNotThrow(() -> {
            controller.createUser(user);
        });
    }
    
    @Test
    void getAllColonies() {
        assertEquals(mockController.getAllColonies(), controller.getAllColonies());
    }
    
    @Test
    void addMetrics() {        
        JsonObject metric1 = new JsonObject();
        metric1.put("date", "2048-05-15")
        .put("time", "17:30:45")
        .put("metricCategory", 3)
        .put("metricValue", 110);
        
        JsonObject metric2 = new JsonObject();
        metric2.put("date", "2048-05-15")
        .put("time", "17:40:45")
        .put("metricCategory", 3)
        .put("metricValue", 110);
        
        JsonObject invalidMetric = new JsonObject();
        invalidMetric.put("date", "invalid")
        .put("time", "invalid")
        .put("metricCategory", 3)
        .put("metricValue", 110);
        JsonArray invalidMetrics = new JsonArray().add(invalidMetric);
        
        JsonArray metrics = new JsonArray();
        metrics.add(metric1);
        metrics.add(metric2);
        
        assertDoesNotThrow(() -> {
            controller.addMetrics(1, metrics);
        });

        assertThrows(IllegalRequestException.class, () -> {
            controller.addMetrics(1, invalidMetrics);
        });
    }
    
    @Test
    void addingDuplicateMetricsFails() {        
        JsonObject metric1 = new JsonObject();
        metric1.put("date", "2048-05-15")
        .put("time", "17:30:45")
        .put("metricCategory", 3)
        .put("metricValue", 110);
        
        JsonObject metric2 = new JsonObject();
        metric2.put("date", "2048-05-15")
        .put("time", "17:40:45")
        .put("metricCategory", 3)
        .put("metricValue", 110);
        
        JsonArray metrics1 = new JsonArray();
        metrics1.add(metric1);
        metrics1.add(metric1);
        
        JsonArray metrics2 = new JsonArray();
        metrics2.add(metric2);
        
        assertThrows(IllegalRequestException.class, () -> {
            controller.addMetrics(1, metrics1);
        });
        
        assertDoesNotThrow(() -> {
            controller.addMetrics(2, metrics2);
        });
        
        assertThrows(IllegalRequestException.class, () -> {
            controller.addMetrics(2, metrics2);
        });
    }

    @Test
    void getMetricsTest() {
        assertEquals(mockController.getMetrics(1, null, null), controller.getMetrics(1, null, null));
    }

    @Test
    void getMetricsWithStartDateTest() {        
        assertEquals(mockController.getMetrics(1, "2084-01-05", null), controller.getMetrics(1, "2084-01-05", null));
    }

    @Test
    void getMetricsWithEndDateTest() {        
        assertEquals(mockController.getMetrics(1, null, "2084-01-05"), controller.getMetrics(1, null, "2084-01-05"));
    }

    @Test
    void getMetricsWithStartAndEndDate() {        
        assertEquals(mockController.getMetrics(1, "2084-01-03", "2084-01-07"), controller.getMetrics(1, "2084-01-03", "2084-01-07"));
    }

    @Test
    void getMetricsWithEndBeforeStareThrows() {
        assertThrows(IllegalRequestException.class, () -> {
            controller.getMetrics(1, "2084-01-05", "2084-01-01");
        });
    }

    @Test
    void getMetricsWithInvalidDatesThrows() {
        assertThrows(IllegalRequestException.class, () -> {
            controller.getMetrics(1, "invalid", "2084-01-01");
        });

        assertThrows(IllegalRequestException.class, () -> {
            controller.getMetrics(1, "2084-01-01", "invalid");
        });

        assertThrows(IllegalRequestException.class, () -> {
            controller.getMetrics(1, "invalid", "invalid");
        });
    }
}
