package be.howest.ti.adria.logic.domain;

import java.sql.Date;
import java.sql.Time;

import io.vertx.core.json.JsonObject;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Metric {
    private final int metricCategory;
    private Date date = null;
    private Time time = null;
    private final double metricValue;
    private final int adriaId;
    private String categoryName = null;
    private String unit = null;
    private static final Logger LOGGER = Logger.getLogger(Metric.class.getName());

    public Metric(int adriaId, JsonObject metric) {
        this.metricCategory = metric.getInteger("metricCategory");
        if (metric.getString("date") != null) {
            this.date = str2date(metric.getString("date"));
        } else {
            throw new IllegalArgumentException("A metric requires a date, time is optional");
        }
        this.time = str2time(metric.getString("time"));
        this.metricValue = metric.getDouble("metricValue");
        this.adriaId = adriaId;
    }

    public Metric(int adriaId, int metricCategory, double metricValue, Date date) {
        this(adriaId, metricCategory, metricValue, date, null);
    }

    public Metric(int adriaId, int metricCategory, double metricValue, Date date, Time time) {
        this.metricCategory = metricCategory;
        this.date = date;
        this.time = time;
        this.metricValue = metricValue;
        this.adriaId = adriaId;
    }

    public int getAdriaId() {
        return adriaId;
    }

    public int getMetricCategory() {
        return metricCategory;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public double getMetricValue() {
        return metricValue;
    }

    public void setCategoryName(String categoryName) {
        if (this.categoryName == null) {
            this.categoryName = categoryName;
        } else {
            throw new IllegalStateException("categoryName was already set");
        }
    }

    public void setCategoryUnit(String unit) {
        if (this.unit == null) {
            this.unit = unit;
        } else {
            throw new IllegalStateException("unit was already set");
        }
    }

    public String getCategoryName() {
        if (this.categoryName != null) {
            return categoryName;
        } else {
            throw new IllegalStateException("Name was not set");
        }
    }

    public String getCategoryUnit() {
        if (this.unit != null) {
            return unit;
        } else {
            throw new IllegalStateException("unit was not set");
        }
    }

    private Date str2date(String date) {
        if (date == null) {
            return null;
        }
        try {
            return Date.valueOf(date);
        } catch (IllegalArgumentException ex) {
            String message = String.format("date \"%s\" invalid, expected format: yyyy-mm-dd", date);
            LOGGER.log(Level.INFO, message, ex);
            throw new IllegalArgumentException(message);
        }
    }

    private Time str2time(String time) {
        if (time == null) {
            return null;
        }
        try {
            return Time.valueOf(time);
        } catch (IllegalArgumentException ex) {
            String message = String.format("time \"%s\" invalid, expected format: hh:mm:ss", time);
            LOGGER.log(Level.INFO, message, ex);
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public String toString() {
        return "{metricCategory=" + metricCategory + ", date=" + date + ", time=" + time + ", metricValue="
        + metricValue + "}";
    }
}
