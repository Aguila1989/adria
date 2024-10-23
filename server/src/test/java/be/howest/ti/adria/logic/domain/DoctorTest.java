package be.howest.ti.adria.logic.domain;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class DoctorTest {
    @Test
    void testGetters() {
        Doctor doc = new Doctor(1, "someRandomName");
        assertEquals(1, doc.getAdriaId());
        assertEquals("someRandomName", doc.getName());
    }
}
