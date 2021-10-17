package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactDto implements Serializable {

    private static final long serialVersionUID = 8123210420854015201L;

    private String firstName;

    private String lastName;

    private String phone;

}
