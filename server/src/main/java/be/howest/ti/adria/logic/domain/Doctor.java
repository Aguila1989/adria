package be.howest.ti.adria.logic.domain;

public class Doctor {
    private final String name;
    private final int adriaId;

    public Doctor(int adriaId, String fullName) {
        this.adriaId = adriaId;
        this.name = fullName;
    }

    public String getName() {
        return name;
    }

    public int getAdriaId() {
        return adriaId;
    }
}
