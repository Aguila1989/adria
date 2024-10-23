package be.howest.ti.adria.logic.domain;

public class DiseaseSymptom {
    private final String disease;
    private final String symptom;
    
    public DiseaseSymptom(String disease, String symptom) {
        this.disease = disease;
        this.symptom = symptom;
    }

    public String getDisease() {
        return disease;
    }

    public String getSymptom() {
        return symptom;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disease == null) ? 0 : disease.hashCode());
        result = prime * result + ((symptom == null) ? 0 : symptom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiseaseSymptom other = (DiseaseSymptom) obj;
        if (disease == null) {
            if (other.disease != null)
                return false;
        } else if (!disease.equals(other.disease))
            return false;
        if (symptom == null) {
            if (other.symptom != null)
                return false;
        } else if (!symptom.equals(other.symptom))
            return false;
        return true;
    }
}
