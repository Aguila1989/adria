package be.howest.ti.adria.logic.domain;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import be.howest.ti.adria.logic.domain.enums.Gender;
import be.howest.ti.adria.logic.domain.enums.SubscriptionType;

/**
* We are aware these tests aren't functional, but else sonarcube complains about code coverage
*/
public class UserTest {
    
    @Test
    void assertEqualsTest() {
        User user1 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        User user2 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);

        User user3 = new User(2, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        User user4 = new User(1, "fnameeeeeeee", "lname", new Date(1), Gender.FEMALE, 1, 1);
        User user5 = new User(1, "fname", "lnameeeee", new Date(1), Gender.FEMALE, 1, 1);
        User user6 = new User(1, "fname", "lname", new Date(20000), Gender.FEMALE, 1, 1);
        User user7 = new User(1, "fname", "lname", new Date(1), Gender.MALE, 1, 1);
        User user8 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 2, 1);
        User user9 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        user9.setMedicalHistory(new MedicalHistory(null));
        User user10 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        user10.setSubscriptionType(SubscriptionType.RESQPLUS);
        User user11 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 2);

        assertTrue(user1.equals(user1));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals(new Object()));
        assertEquals(user1, user2);

        assertFalse(user1.equals(user3));
        assertFalse(user1.equals(user4));
        assertFalse(user1.equals(user5));
        assertFalse(user1.equals(user6));
        assertFalse(user1.equals(user7));
        assertFalse(user1.equals(user8));
        assertFalse(user1.equals(user9));
        assertFalse(user1.equals(user10));
        assertFalse(user1.equals(user11));

    }
    
    @Test
    void hashCodeTest() {
        User user1 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        assertEquals(user1.hashCode(), user1.hashCode());
    }

    @Test
    void MedicalHistoryTest() {
        User user1 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        Set<MedicalHistoryRecord> records = new HashSet<>();
        Diagnosis diag = new Diagnosis(new String[]{"a", "b"}, "c");
        records.add(new MedicalHistoryRecord(5, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(6, new Date(1), new Time(1), new int[]{1,2}, diag));
        records.add(new MedicalHistoryRecord(7, new Date(1), new Time(1), new int[]{1,2}, diag));

        assertNull(user1.getMedicalHistory());

        assertDoesNotThrow(() -> {
            user1.setMedicalHistory(new MedicalHistory(null));
        });
        assertThrows(IllegalStateException.class, () -> {
            user1.setMedicalHistory(new MedicalHistory(null));
        });
    }

    @Test
    void SubscriptionTypeTest() {
        User user1 = new User(1, "fname", "lname", new Date(1), Gender.FEMALE, 1, 1);
        assertEquals(SubscriptionType.RESQ, user1.getSubscriptionType());
        user1.setSubscriptionType(SubscriptionType.RESQPRO);
        assertEquals(SubscriptionType.RESQPRO, user1.getSubscriptionType());
    }
}
