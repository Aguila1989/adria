package be.howest.ti.adria.logic.domain.enums;

public enum SubscriptionType {

    RESQ(1),
    RESQPLUS(2),
    RESQPRO(3);

    private int id;

    SubscriptionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
