package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable {

    private static final long serialVersionUID = -5333250276781691289L;

    private Long id;

    private String dateOfBirth;

    private Sex sex = Sex.unknown;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
