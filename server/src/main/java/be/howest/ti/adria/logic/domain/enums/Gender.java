package be.howest.ti.adria.logic.domain.enums;

import java.util.Arrays;

public enum Gender {

    MALE("M"),
    FEMALE("F");

    private String genderCode;

    Gender(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderCode() {
        return this.genderCode;
    }

    public static Gender getGender(String genderCode) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getGenderCode().equals(genderCode))
                .findFirst()
                .orElse(null);
    }
}
