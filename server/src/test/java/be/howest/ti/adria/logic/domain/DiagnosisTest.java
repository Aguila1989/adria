package be.howest.ti.adria.logic.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * We are aware these tests aren't functional, but else sonarcube complains about code coverage
 */
public class DiagnosisTest {
    @Test
    void testGetCondition() {
        Diagnosis diag = new Diagnosis(new String[]{"cough", "sour throat"}, "common cold");
        assertEquals("common cold", diag.getCondition());
    }

    @Test
    void testGetSymptoms() {
        Diagnosis diag = new Diagnosis(new String[]{"cough", "sour throat"}, "common cold");
        assertArrayEquals(new String[]{"cough", "sour throat"}, diag.getSymptoms());
    }

    @Test
    void testSetCondition() {
        Diagnosis diag = new Diagnosis(new String[]{"cough", "sour throat"}, "common cold");
        diag.setCondition("serious cold");
        assertEquals("serious cold", diag.getCondition());
    }

    @Test
    void testSetSymptoms() {
        Diagnosis diag = new Diagnosis(new String[]{"cough", "sour throat"}, "common cold");
        diag.setSymptoms(new String[]{"cough", "slightly sour throat"});
        assertArrayEquals(new String[]{"cough", "slightly sour throat"}, diag.getSymptoms());
    }
}
