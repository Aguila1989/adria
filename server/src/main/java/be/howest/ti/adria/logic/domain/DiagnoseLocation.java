package be.howest.ti.adria.logic.domain;

import io.vertx.core.json.JsonObject;

public class DiagnoseLocation {
    private final double lon;
    private final double lat;
    private final int colonyId;
    
    public DiagnoseLocation(JsonObject location) {
        //guard clause for null location
        if (location == null) {
            this.lon = 404;
            this.lat = 404;
            this.colonyId = -1;
            return;
        }
        
        this.colonyId = location.getInteger("colonyId") == null ? -1 : location.getInteger("colonyId"); //-1 => colony was not provided
        
        
        double parsedLon = 404;
        double parsedLat = 404;
        JsonObject lonlatLocation = location.getJsonObject("location");
        try {
            if (lonlatLocation != null && lonlatLocation.containsKey("lon") && lonlatLocation.containsKey("lat")) {
                parsedLon = lonlatLocation.getDouble("lon");
                parsedLat = lonlatLocation.getDouble("lat");
            }
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException("lon and lat need to me numbers");
        }
        
        
        try {
            if (parsedLon != 404 && parsedLat != 404) {
                this.lon = validateRange(parsedLon, -180, 180);
                this.lat = validateRange(parsedLat, -90, 90);
            } else {
                this.lat = parsedLat;
                this.lon = parsedLon;
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("lon must be between -180 and 180, lat must be between -90 and 90");
        }
    }
    
    /**
    * if there was no lon this is marked with a return value of 404
    * @return
    */
    public double getLon() {
        return lon;
    }   
    
    /**
    * if there was no lat this is marked with a return value of 404
    * @return
    */
    public double getLat() {
        return lat;
    }
    
    public int getColony() {
        return colonyId;
    }
    
    private double validateRange(double numToValidate, double min, double max) {
        if (numToValidate < min || numToValidate > max) {
            throw new IllegalArgumentException("num not within range");
        }
        return numToValidate;
    }
}
