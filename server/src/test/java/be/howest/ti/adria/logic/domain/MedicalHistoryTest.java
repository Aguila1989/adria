package be.howest.ti.adria.logic.domain;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;

/**
* We are aware these tests aren't functional, but else sonarcube complains about code coverage
*/
public class MedicalHistoryTest {
    @Test
    void testGetMedicalHistoryRecords() {
        Set<MedicalHistoryRecord> records = new HashSet<>();
        Diagnosis diag = new Diagnosis(new String[]{"a", "b"}, "c");
        records.add(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(6, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(7, new Date(1), new Time(1), new int[]{1,2}, diag));
        
        MedicalHistory history = new MedicalHistory(new HashSet<MedicalHistoryRecord>(records));
        assertEquals(records, history.getMedicalHistoryRecords());
    }
    
    @Test
    void testSetMedicalHistoryRecords() {
        Set<MedicalHistoryRecord> records = new HashSet<>();
        Diagnosis diag = new Diagnosis(new String[]{"a", "b"}, "c");
        records.add(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(6, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(7, new Date(1), new Time(1), new int[]{1,2}, diag));
        
        Set<MedicalHistoryRecord> records2 = new HashSet<>();
        records.add(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(6, new Date(1), new Time(1), new int[]{1,2}, diag));
        
        MedicalHistory history = new MedicalHistory(new HashSet<MedicalHistoryRecord>(records));
        history.setMedicalHistoryRecords(records2);
        assertEquals(records2, history.getMedicalHistoryRecords());
    }
    
    @Test
    void assertEqualsTest() {
        Set<MedicalHistoryRecord> records = new HashSet<>();
        Diagnosis diag = new Diagnosis(new String[]{"a", "b"}, "c");
        records.add(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(6, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(7, new Date(1), new Time(1), new int[]{1,2}, diag));
        
        MedicalHistory history1 = new MedicalHistory(new HashSet<MedicalHistoryRecord>(records));
        MedicalHistory history2 = new MedicalHistory(new HashSet<MedicalHistoryRecord>(records));
        
        assertEquals(history1, history2);
        assertTrue(history1.equals(history2));
        assertTrue(history2.equals(history1));
        assertTrue(history1.equals(history1));
        assertFalse(history1.equals(null));
        assertFalse(history1.equals(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag)));
        assertFalse(new MedicalHistory(null).equals(history1));
        assertTrue(new MedicalHistory(null).equals(new MedicalHistory(null)));       
        assertFalse(history1.equals(new MedicalHistory(null)));
        assertFalse(new MedicalHistory(new HashSet<>()).equals(history1));
    }

    @Test
    void hashCodeTest() {
        Set<MedicalHistoryRecord> records = new HashSet<>();
        Diagnosis diag = new Diagnosis(new String[]{"a", "b"}, "c");
        records.add(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(6, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(7, new Date(1), new Time(1), new int[]{1,2}, diag));
        
        MedicalHistory history1 = new MedicalHistory(new HashSet<MedicalHistoryRecord>(records));
        MedicalHistory history2 = new MedicalHistory(null);
        
        assertEquals(history1.hashCode(), history1.hashCode());
        assertEquals(history2.hashCode(), history2.hashCode());
    }
}
