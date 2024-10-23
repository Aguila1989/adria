package be.howest.ti.adria.logic.data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.*;
import java.util.Map;

import be.howest.ti.adria.logic.domain.Metric;
import be.howest.ti.adria.logic.domain.User;
import be.howest.ti.adria.logic.domain.enums.Gender;
import be.howest.ti.adria.logic.exceptions.RepositoryException;
import be.howest.ti.adria.web.exceptions.IllegalRequestException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.howest.ti.adria.logic.controller.Controller;
import be.howest.ti.adria.logic.controller.MockController;
import io.vertx.core.json.JsonObject;

class AdriaH2RepositoryTest {
    private static final String URL = "jdbc:h2:./db-05";

    @BeforeEach
    void setupTestSuite() {
        Repositories.shutdown();
        JsonObject dbProperties = new JsonObject(Map.of("url",URL,
        "username", "",
        "password", "",
        "webconsole.port", 9000 ));
        Repositories.configure(dbProperties);
    }

    @Test
    void getSubscriptionTypesTest() {
        Controller mockController = new MockController();
        Assertions.assertEquals(mockController.getSubscriptionTypes(), Repositories.getH2Repo().getSubscriptionTypes());
    }

    @Test
    void updateSubscriptionTypeTest() {
        assertDoesNotThrow(() -> {
            Repositories.getH2Repo().updateSubscriptionType(2, 1);
        });
    }

    @Test
    void getBloodGroupIDTest() {
        Assertions.assertEquals(1, Repositories.getH2Repo().getBloodGroupIDFromBloodGroup("O-"));
        Assertions.assertEquals(7, Repositories.getH2Repo().getBloodGroupIDFromBloodGroup("AB+"));
        Assertions.assertEquals(-1, Repositories.getH2Repo().getBloodGroupIDFromBloodGroup("ABDD"));
    }

    @Test
    void createUserTest() {
        Assertions.assertDoesNotThrow(() -> {
            Repositories.getH2Repo().createUser(new User(45, "fname", "lname", new Date(1), Gender.MALE, 1, 1));
        });

        Assertions.assertThrows(RepositoryException.class ,() -> {
            Repositories.getH2Repo().createUser(new User(1, "fname", "lname", new Date(1), Gender.MALE, 1, 1));
        });
    }

    @Test
    void getUserInfoByIDTest() {
        Controller mockController = new MockController();
        assertEquals(mockController.getUserInfoByID(1).toString(),
        Repositories.getH2Repo().getUserInfoByID(1).toString());
    }

    @Test
    void getMedicalHistoryByIdTest() {
        Controller mockController = new MockController();
        assertEquals(mockController.getMedicalHistoryByID(1).toString(),
        Repositories.getH2Repo().getMedicalHistoryById(1).toString());
    }

    @Test
    void getAllColoniesTest() {
        JsonObject expected = new JsonObject("{"+
        "   \"1\": \"Adria\"," +
        "   \"2\": \"Mars\"," +
        "   \"3\": \"Remote-1\"," +
        "   \"4\": \"Remote-2\"," +
        "   \"5\": \"Remote-3\"," +
        "   \"6\": \"Remote-4\"" +
        "}");
        assertEquals(expected.toString(), Repositories.getH2Repo().getColonies().toString());
    }

    @Test
    void addMetricTest() {
        H2Repository repo = Repositories.getH2Repo();

        JsonObject metric1json = new JsonObject();
        metric1json.put("date", "2048-05-15")
        .put("time", "17:30:45")
        .put("metricCategory", 3)
        .put("metricValue", 110);
        Metric metric1 = new Metric(1, metric1json);

        assertDoesNotThrow(() -> {
            repo.addMetric(metric1);
        });

        assertThrows(RepositoryException.class, () -> {
            repo.addMetric(metric1);
        });
    }
}
