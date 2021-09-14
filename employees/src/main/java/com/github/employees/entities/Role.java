package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    public static final String ADMIN = "ADMIN";

    public static final String MANAGER = "MANAGER";

    public static final String DEVELOPER = "MANAGER";

    private static final long serialVersionUID = -5524420904663290354L;

    private Long id;

    private String role;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Role(@NotBlank(message = "Role is Required") String role) {
        this.role = role;
    }

}
