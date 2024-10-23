package be.howest.ti.adria.logic.domain;

import java.sql.Time;
import java.util.Arrays;
import java.sql.Date;

public class MedicalHistoryRecord {

    private int adriaId;
    private Date date;
    private Time time; // to do: Look if this is the right type to use. It is from the SQL.Time class
    private int[] diagnosedBy;
    private Diagnosis diagnosis;
    private DiagnoseLocation location = null;
    private int entryId = -1;

    public MedicalHistoryRecord(int adriaId, Date date, Time time, int[] diagnosedBy, Diagnosis diagnosis) {
        this.adriaId = adriaId;
        this.date = date;
        this.time = time;
        this.diagnosedBy = diagnosedBy;
        this.diagnosis = diagnosis;
    }

    public void setEntryId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("id may not be less then one");
        }

        if (this.entryId == -1) {
            this.entryId = id;
        } else {
            throw new IllegalStateException("entryId already set");
        }
    }

    public int getEntryId() {
        return entryId;
    }

    public void setLocation(DiagnoseLocation location) {
        if (this.location == null) {
            this.location = location;
        } else {
            throw new IllegalStateException("location already set");
        }
    }

    public DiagnoseLocation getLocation() {
        return location;
    }

    public int getAdriaId() {
        return adriaId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int[] getDiagnosedBy() {
        return diagnosedBy;
    }

    public void setDiagnosedBy(int[] diagnosedBy) {
        this.diagnosedBy = diagnosedBy;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + adriaId;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + Arrays.hashCode(diagnosedBy);
        result = prime * result + ((diagnosis == null) ? 0 : diagnosis.hashCode());
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
        MedicalHistoryRecord other = (MedicalHistoryRecord) obj;
        if (adriaId != other.adriaId)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (!Arrays.equals(diagnosedBy, other.diagnosedBy))
            return false;
        if (diagnosis == null) {
            if (other.diagnosis != null)
                return false;
        } else if (!diagnosis.equals(other.diagnosis))
            return false;
        return true;
    }
}
