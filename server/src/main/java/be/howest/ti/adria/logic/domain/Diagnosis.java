package be.howest.ti.adria.logic.domain;

public class Diagnosis {

    private String[] symptoms;
    private String condition;

    public Diagnosis(String[] symptoms, String condition) {
        this.symptoms = symptoms;
        this.condition = condition;
    }

    public String[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String[] symptoms) {
        this.symptoms = symptoms;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
