package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {

    @NotBlank
    private String email;

    @NotBlank
    private String pass;

    @NotBlank
    private String confirmPass;

}
