package com.github.employees.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Profile implements Serializable {

    private static final long serialVersionUID = -5333250276781691289L;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String dateOfBirth;

    private Sex sex = Sex.unknown;

    public Profile(String firstName, String lastName, String patronymic, String dateOfBirth, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
    }

    public static Profile defaultProfile(String firstName, String lastName, String patronymic) {
        return new Profile(firstName, lastName, patronymic, "unknown", Sex.unknown);
    }

}
