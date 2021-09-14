package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable {

    public static final String ADMIN = "ADMIN";

    public static final String MANAGER = "MANAGER";

    public static final String DEVELOPER = "MANAGER";

    private static final long serialVersionUID = -5524420904663290354L;

    private Long id;

    private String role;

}
