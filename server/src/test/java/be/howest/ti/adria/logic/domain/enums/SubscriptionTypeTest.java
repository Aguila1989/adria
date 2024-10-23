package be.howest.ti.adria.logic.domain.enums;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class SubscriptionTypeTest {
    @Test
    void testGetId() {
        assertEquals(1, SubscriptionType.RESQ.getId());
        assertEquals(2, SubscriptionType.RESQPLUS.getId());
        assertEquals(3, SubscriptionType.RESQPRO.getId());        
    }
}
