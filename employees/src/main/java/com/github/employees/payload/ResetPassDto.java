package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassDto implements Serializable {

    private static final long serialVersionUID = -2891754208417175650L;

    @NotBlank(
            message = "Email is required"
    )
    private String email;

    @NotBlank(
            message = "Email is pass"
    )
    private String pass;

    @NotBlank(
            message = "Email is confirm pass"
    )
    private String confirmPass;
}
