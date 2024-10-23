package be.howest.ti.adria.logic.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;

public class MetricSummaryTest {
    @Test
    void constructorTest() {
        //valid
        Metric metric1 = new Metric(1, 1, 1, Date.valueOf("2084-01-01"));
        metric1.setCategoryName("name");
        Metric metric2 = new Metric(1, 2, 1, Date.valueOf("2084-01-01"));
        metric2.setCategoryName("name");
        Metric metric3 = new Metric(1, 3, 1, Date.valueOf("2084-01-01"));
        metric3.setCategoryName("name");

        //invalid
        Metric metric4 = new Metric(1, 1, 1, Date.valueOf("2084-01-01")); //no Category name set (required by MetricSummary)

        List<Metric> validMetricList = new ArrayList<>();
        validMetricList.add(metric1);
        validMetricList.add(metric2);
        validMetricList.add(metric3);

        List<Metric> invalidMetricList = new ArrayList<>();
        invalidMetricList.addAll(validMetricList);
        invalidMetricList.add(metric4);

        assertDoesNotThrow(() -> {
            new MetricSummary(validMetricList);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new MetricSummary(invalidMetricList);
        });
    }

    @Test
    void metricsWithTimeGetLeftOut() {
        Metric metric1 = new Metric(1, 1, 1.555, Date.valueOf("2084-01-01"));
        metric1.setCategoryName("name1");
        Metric metric2 = new Metric(1, 2, 1.7, Date.valueOf("2084-01-01"));
        metric2.setCategoryName("name2");
        Metric metric3 = new Metric(1, 3, 1.2, Date.valueOf("2084-01-01"));
        metric3.setCategoryName("name3");
        Metric metric4 = new Metric(1, 3, 1.2, Date.valueOf("2084-01-01"), Time.valueOf("17:00:00"));
        metric4.setCategoryName("name4");

        List<Metric> validMetricList = new ArrayList<>();
        validMetricList.add(metric1);
        validMetricList.add(metric2);
        validMetricList.add(metric3);
        validMetricList.add(metric4);
        
        assertEquals("{\"2084-01-01\":{\"name1\":1.555,\"name2\":1.7,\"name3\":1.2}}",
        new MetricSummary(validMetricList).toJson().toString());
    }

    @Test
    void testToJson() {
        Metric metric1 = new Metric(1, 1, 1.555, Date.valueOf("2084-01-01"));
        metric1.setCategoryName("name1");
        Metric metric2 = new Metric(1, 2, 1.7, Date.valueOf("2084-01-01"));
        metric2.setCategoryName("name2");
        Metric metric3 = new Metric(1, 3, 1.2, Date.valueOf("2084-01-01"));
        metric3.setCategoryName("name3");

        List<Metric> validMetricList = new ArrayList<>();
        validMetricList.add(metric1);
        validMetricList.add(metric2);
        validMetricList.add(metric3);
        
        assertEquals("{\"2084-01-01\":{\"name1\":1.555,\"name2\":1.7,\"name3\":1.2}}",
        new MetricSummary(validMetricList).toJson().toString());
    }
}
