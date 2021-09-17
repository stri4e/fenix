package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployAuthDto {

    @NotBlank(
            message = "User name is required"
    )
    private String userName;

    @NotBlank(
            message = "User name is required"
    )
    private String pass;

}
