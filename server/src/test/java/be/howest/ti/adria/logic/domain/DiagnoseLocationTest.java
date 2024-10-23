package be.howest.ti.adria.logic.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import io.vertx.core.json.JsonObject;


public class DiagnoseLocationTest {
    @Test
    void nullJsonObjectHasUnsetReturns() {
        DiagnoseLocation location = new DiagnoseLocation(null);
        assertTrue(Math.abs(location.getLon() - 404) < 0.01);
        assertTrue(Math.abs(location.getLat() - 404) < 0.01);
        assertEquals(-1, location.getColony());
    }

    @Test
    void noLonLatReturnsUnsetLonLat() {
        JsonObject json = new JsonObject()
        .put("colonyId", 1);

        DiagnoseLocation location = new DiagnoseLocation(json);
        assertTrue(Math.abs(location.getLon() - 404) < 0.01);
        assertTrue(Math.abs(location.getLat() - 404) < 0.01);
    }

    @Test
    void illegalLonOrLatThrows() {
        JsonObject json1 = new JsonObject()
        .put("location", new JsonObject()
            .put("lon", 400)
            .put("lat", 400));

        JsonObject json2 = new JsonObject()
        .put("location", new JsonObject()
            .put("lon", 89)
            .put("lat", 400));

        JsonObject json3 = new JsonObject()
        .put("location", new JsonObject()
            .put("lon", 400)
            .put("lat", 89));

        assertThrows(IllegalArgumentException.class, () -> {
            new DiagnoseLocation(json1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DiagnoseLocation(json2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DiagnoseLocation(json3);
        });
    }

    @Test
    void lonLatMustBeNumbers() {
        JsonObject json1 = new JsonObject()
        .put("location", new JsonObject()
            .put("lon", "a")
            .put("lat", new String[]{"a"}));

        assertThrows(IllegalArgumentException.class, () -> {
            new DiagnoseLocation(json1);
        });
    }

    @Test
    void partiaLonLatIsIgnored() {
        JsonObject json1 = new JsonObject()
        .put("colonyId", 1)
        .put("location", new JsonObject()
            .put("lon", 400));

        JsonObject json2 = new JsonObject()
        .put("colonyId", 1)
        .put("location", new JsonObject()
            .put("lat", 400));

        DiagnoseLocation location1 = new DiagnoseLocation(json1);
        DiagnoseLocation location2 = new DiagnoseLocation(json2);

        assertTrue(Math.abs(location1.getLon() - 404) < 0.01);
        assertTrue(Math.abs(location1.getLat() - 404) < 0.01);
        assertTrue(Math.abs(location2.getLon() - 404) < 0.01);
        assertTrue(Math.abs(location2.getLat() - 404) < 0.01);
    }
}
