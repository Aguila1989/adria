package be.howest.ti.adria.logic.domain;

import be.howest.ti.adria.logic.domain.enums.Gender;
import be.howest.ti.adria.logic.domain.enums.SubscriptionType;

import java.sql.Date;
import java.util.Objects;

public class User {

    private long adriaID;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private int bloodTypeID;
    private MedicalHistory medicalHistory = null;
    private SubscriptionType subscriptionType = null;
    private int colonyID;

    public User(long adriaID, String firstName, String lastName, Date birthDate, Gender gender, int bloodTypeID, int colonyID) {
        this.adriaID = adriaID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bloodTypeID = bloodTypeID;
        this.colonyID = colonyID;
        this.subscriptionType = SubscriptionType.RESQ;
    }

    public long getAdriaID() {
        return adriaID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getBloodTypeID() {
        return bloodTypeID;
    }

    public int getColonyID() {
        return colonyID;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory history) {
        if (medicalHistory == null) {
            this.medicalHistory = history;
        } else {
            throw new IllegalStateException("can't change history if the user already has one");
        }
    }

    public void setSubscriptionType(SubscriptionType type) {
        this.subscriptionType = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return adriaID == user.adriaID && colonyID == user.colonyID && firstName.equals(user.firstName) && lastName.equals(user.lastName) && birthDate.equals(user.birthDate) && gender == user.gender && bloodTypeID == user.bloodTypeID && Objects.equals(medicalHistory, user.medicalHistory) && subscriptionType == user.subscriptionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adriaID, firstName, lastName, birthDate, gender, bloodTypeID, medicalHistory, subscriptionType, colonyID);
    }

}
