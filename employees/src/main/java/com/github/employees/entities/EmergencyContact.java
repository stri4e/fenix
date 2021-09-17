package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact implements Serializable {

    private static final long serialVersionUID = 8123210420854015201L;

    private String firstName;

    private String lastName;

    private String phone;

    public static EmergencyContact defaultEmergencyContact() {
        return new EmergencyContact(
                "unknown",
                "unknown",
                "unknown"
        );
    }
}
