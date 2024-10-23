package be.howest.ti.adria.logic.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

import io.vertx.core.json.JsonObject;

public class MetricTest {
    @Test
    void testConstructor() {
        JsonObject jsonMetric = new JsonObject("{" +
        "  \"metricCategory\": 3," +
        "  \"date\":\"2084-1-17\"," +
        "  \"time\": \"17:00:00\"," +
        "  \"metricValue\": 3.5555" +
        "}");

        Metric metric = new Metric(1, jsonMetric);
        metric.setCategoryName("categoryName");
        metric.setCategoryUnit("unit");

        assertEquals(Date.valueOf("2084-1-17"), metric.getDate());
        assertEquals(3, metric.getMetricCategory());
        assertTrue(Math.abs(3.555 - metric.getMetricValue()) < 0.001); //never immediatly compare doubles
        assertEquals(Time.valueOf("17:00:00"), metric.getTime());
        assertEquals("categoryName", metric.getCategoryName());
        assertEquals("unit", metric.getCategoryUnit());
    }

    @Test
    void settingTheCategoryNameTwiceThrows() {
        Metric metric = new Metric(1, 1, 1, Date.valueOf("2084-01-01"));
        assertDoesNotThrow(() -> {
            metric.setCategoryName("someName");
        });

        assertThrows(IllegalStateException.class, () -> {
            metric.setCategoryName("thisShouldFail");
        });
    }

    @Test
    void settingTheUnitTwiceThrows() {
        Metric metric = new Metric(1, 1, 1, Date.valueOf("2084-01-01"), Time.valueOf("07:00:00"));
        assertDoesNotThrow(() -> {
            metric.setCategoryUnit("unit");
        });

        assertThrows(IllegalStateException.class, () -> {
            metric.setCategoryUnit("thisShouldFail");
        });
    }

    @Test
    void gettingAnUnsetUnitThrows() {
        Metric metric = new Metric(1, 1, 1, Date.valueOf("2084-01-01"), Time.valueOf("07:00:00"));

        assertThrows(IllegalStateException.class, () -> {
            metric.getCategoryUnit();
        });
    }

    @Test
    void gettingAnUnsetCategoryNameThrows() {
        Metric metric = new Metric(1, 1, 1, Date.valueOf("2084-01-01"), Time.valueOf("07:00:00"));

        assertThrows(IllegalStateException.class, () -> {
            metric.getCategoryName();
        });
    }

    @Test
    void metricWithoutDateThrows() {
        JsonObject jsonMetric = new JsonObject("{" +
        "  \"metricCategory\": 3," +
        "  \"time\": \"17:00:00\"," +
        "  \"metricValue\": 3.5555" +
        "}");

        assertThrows(IllegalArgumentException.class, () -> {
            new Metric(1, jsonMetric);
        });
    }

    @Test
    void metricTimeIsOptional() {
        JsonObject jsonMetric1 = new JsonObject("{" +
        "  \"metricCategory\": 3," +
        "  \"date\":\"2084-1-17\"," +
        "  \"time\": \"17:00:00\"," +
        "  \"metricValue\": 3.5555" +
        "}");

        JsonObject jsonMetric2 = new JsonObject("{" +
        "  \"metricCategory\": 3," +
        "  \"date\":\"2084-1-17\"," +
        "  \"metricValue\": 3.5555" +
        "}");

        assertDoesNotThrow(() -> {
            new Metric(1, jsonMetric1);
        });

        assertDoesNotThrow(() -> {
            new Metric(1, jsonMetric2);
        });
    }

    @Test
    void invalidDateOrTimeThrows() {
        JsonObject jsonMetricInvalidDate = new JsonObject("{" +
        "  \"metricCategory\": 3," +
        "  \"date\":\"20,hgkhgkhgkjhg17\"," +
        "  \"time\": \"17:00:00\"," +
        "  \"metricValue\": 3.5555" +
        "}");

        JsonObject jsonMetricInvalidTime = new JsonObject("{" +
        "  \"metricCategory\": 3," +
        "  \"date\":\"2084-1-17\"," +
        "  \"time\": \";hgkhgkhgkjghkjh\"," +
        "  \"metricValue\": 3.5555" +
        "}");

        assertThrows(IllegalArgumentException.class, () -> {
            new Metric(1, jsonMetricInvalidDate);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Metric(1, jsonMetricInvalidTime);
        });
    }
}
