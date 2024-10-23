package be.howest.ti.adria.logic.domain;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.vertx.core.json.JsonObject;

public class MetricSummary {
    private List<Metric> metrics;
    
    public MetricSummary(List<Metric> metrics) {
        metrics.stream().forEach(metric -> {
            try {
                metric.getCategoryName();
            } catch (IllegalStateException ex) {
                throw new IllegalArgumentException("All metrics for the summary need a categoryName to be set");
            }
        });

        this.metrics = metrics;
    }
    
    public JsonObject toJson() {
        JsonObject res = new JsonObject();

        Map<Date, List<Metric>> groupedByDate = metrics.stream().collect(Collectors.groupingBy(Metric::getDate));
        groupedByDate.keySet().stream().forEach(date -> {
            List<Metric> metricsOfDate = groupedByDate.get(date);
            JsonObject dayOverview = new JsonObject();

            metricsOfDate.stream()
                .filter(metric -> metric.getTime() == null) //only include average metrics
                .forEach(metric -> dayOverview.put(metric.getCategoryName(), metric.getMetricValue()));
            
            res.put(date.toString(), dayOverview);
        });

        return res;
    }
}
