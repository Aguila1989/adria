package be.howest.ti.adria.logic.domain;

import java.util.Set;

public class MedicalHistory {

    private Set<MedicalHistoryRecord> medicalHistoryRecords;

    public MedicalHistory(Set<MedicalHistoryRecord> medicalHistoryRecords) {
        this.medicalHistoryRecords = medicalHistoryRecords;
    }

    public Set<MedicalHistoryRecord> getMedicalHistoryRecords() {
        return this.medicalHistoryRecords;
    }

    public void setMedicalHistoryRecords(Set<MedicalHistoryRecord> medicalHistoryRecords) {
        this.medicalHistoryRecords = medicalHistoryRecords;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((medicalHistoryRecords == null) ? 0 : medicalHistoryRecords.hashCode());
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
        MedicalHistory other = (MedicalHistory) obj;
        if (medicalHistoryRecords == null) {
            if (other.medicalHistoryRecords != null)
                return false;
        } else if (!medicalHistoryRecords.equals(other.medicalHistoryRecords))
            return false;
        return true;
    }

}
