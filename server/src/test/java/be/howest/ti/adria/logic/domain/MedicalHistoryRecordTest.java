package be.howest.ti.adria.logic.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.sql.Date;

import org.junit.jupiter.api.Test;

public class MedicalHistoryRecordTest {
    @Test
    void testSetAndGet() {
        MedicalHistoryRecord record = new MedicalHistoryRecord(1, new Date(1), new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
                
        record.setDate(new Date(2000));
        assertEquals(new Date(2000), record.getDate());
        
        record.setTime(new Time(2000));
        assertEquals(new Time(2000), record.getTime());
        
        record.setDiagnosedBy(new int[]{7,5,9});
        assertArrayEquals(new int[]{7,5,9}, record.getDiagnosedBy());
        
        Diagnosis newDiagnosis = new Diagnosis(new String[]{"headache", "frustration"}, "debugging software engineer");
        record.setDiagnosis(newDiagnosis);
        assertEquals(newDiagnosis, record.getDiagnosis());
    }
    
    @Test
    void equalsTest() {
        MedicalHistoryRecord record1 = new MedicalHistoryRecord(1, new Date(1), new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record2 = new MedicalHistoryRecord(2, new Date(1), new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        
        MedicalHistoryRecord record3 = new MedicalHistoryRecord(2, null, new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record4 = new MedicalHistoryRecord(2, new Date(3), new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record5 = new MedicalHistoryRecord(2, new Date(7), new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        
        MedicalHistoryRecord record6 = new MedicalHistoryRecord(2, new Date(7), null, new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record7 = new MedicalHistoryRecord(2, new Date(7), new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record8 = new MedicalHistoryRecord(2, new Date(7), new Time(2), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        
        MedicalHistoryRecord record9 = new MedicalHistoryRecord(2, new Date(7), new Time(2), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record11 = new MedicalHistoryRecord(2, new Date(7), new Time(2), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "coughign"}, "cold"));
        MedicalHistoryRecord record12 = new MedicalHistoryRecord(2, new Date(7), new Time(2), new int[]{1,3}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        
        
        
        
        assertTrue(record1.equals(record1));
        assertFalse(record1.equals(null));
        assertFalse(record1.equals(new Object()));
        assertFalse(record1.equals(record2));
        assertFalse(record3.equals(record1));
        assertFalse(record3.equals(record4));
        assertFalse(record4.equals(record5));
        assertFalse(record6.equals(record7));
        assertFalse(record7.equals(record8));
        assertFalse(record9.equals(record11));
        assertFalse(record11.equals(record12));
    }
    
    @Test
    void hashCodeTest() {
        MedicalHistoryRecord record1 = new MedicalHistoryRecord(1, null, new Time(1), new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        MedicalHistoryRecord record2 = new MedicalHistoryRecord(1, new Date(1), null, new int[]{1,2}, new Diagnosis(new String[]{"sweat", "cough"}, "cold"));
        assertEquals(record1.hashCode(), record1.hashCode());
        assertEquals(record2.hashCode(), record2.hashCode()); 
    }
}
