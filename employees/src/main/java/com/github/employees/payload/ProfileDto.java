package com.github.employees.payload;

import com.github.employees.entities.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto implements Serializable {

    private static final long serialVersionUID = -5333250276781691289L;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String dateOfBirth;

    private Sex sex = Sex.unknown;

}
