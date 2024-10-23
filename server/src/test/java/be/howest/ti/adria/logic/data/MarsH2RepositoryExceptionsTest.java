package be.howest.ti.adria.logic.data;

import java.sql.Date;
import java.util.Map;

import be.howest.ti.adria.logic.domain.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import be.howest.ti.adria.logic.domain.Metric;
import be.howest.ti.adria.logic.domain.User;
import be.howest.ti.adria.logic.exceptions.RepositoryException;
import io.vertx.core.json.JsonObject;

class AdriaH2RepositoryExceptionsTest {
    
    private static final String URL = "jdbc:h2:./db-05";
    
    @Test
    void getH2RepoWithNoDbFails() {
        // Arrange
        Repositories.shutdown();
        
        // Act + Assert
        Assertions.assertThrows(RepositoryException.class, Repositories::getH2Repo);
    }
    
    @Test
    void functionsWithSQLExceptionFailsNicely() {
        JsonObject dbProperties = new JsonObject(Map.of("url",URL,
        "username", "",
        "password", "",
        "webconsole.port", 9000 ));
        Repositories.shutdown();
        Repositories.configure(dbProperties);
        H2Repository repo = Repositories.getH2Repo();
        repo.cleanUp();
        
        Assertions.assertThrows(RepositoryException.class, repo::getSubscriptionTypes);
        Assertions.assertThrows(RepositoryException.class, () -> repo.getBloodGroupIDFromBloodGroup("O+"));
        User user = new User(45, "firts", "last", new Date(1), Gender.MALE, 1, 1);
        Assertions.assertThrows(RepositoryException.class, () -> repo.createUser(user));
        Assertions.assertThrows(RepositoryException.class, () -> repo.updateSubscriptionType(1, 1));
        Assertions.assertThrows(RepositoryException.class, () -> repo.getUserInfoByID(1));
        Assertions.assertThrows(RepositoryException.class, () -> repo.getMedicalHistoryById(1));
        Assertions.assertThrows(RepositoryException.class, repo::getColonies);
        Assertions.assertThrows(RepositoryException.class, () ->  {
            repo.addMetric(
                new Metric(1, new JsonObject()
                                    .put("date", "2048-05-15")
                                    .put("time", "17:40:45")
                                    .put("metricCategory", 3)
                                    .put("metricValue", 110)));
        });
        Assertions.assertThrows(RepositoryException.class, () -> {
            repo.getMetrics(1, null, null);
        });
    }
    
    
}
