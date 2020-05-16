package com.github.users.center.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for changed pass.")
public class ForgotPassDto {

    @ApiModelProperty(
            value = "User email.",
            example = "alex@gmail.com"
    )
    @NotBlank(
            message = "Email is required"
    )
    private String email;

    @ApiModelProperty(
            value = "User password.",
            example = "password"
    )
    @NotBlank(
            message = "Email is pass"
    )
    private String pass;

    @ApiModelProperty(
            value = "User confirm.",
            example = "password"
    )
    @NotBlank(
            message = "Email is confirm pass"
    )
    private String confirmPass;
}
