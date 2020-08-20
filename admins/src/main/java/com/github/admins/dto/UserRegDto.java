package com.github.admins.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for create new user on this server.")
public class UserRegDto {

    @ApiModelProperty(
            value = "User firs name.",
            example = "Alex"
    )
    @NotBlank(
            message = "First name is required"
    )
    private String fName;

    @ApiModelProperty(
            value = "User last name.",
            example = "Northred."
    )
    @NotBlank(
            message = "Last name is required"
    )
    private String lName;

    @ApiModelProperty(
            value = "User login.",
            example = "Ackela"
    )
    @NotBlank(
            message = "Login is required"
    )
    private String login;

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
            message = "Password is required"
    )
    private String pass;

    @ApiModelProperty(
            value = "User last name.",
            example = "password"
    )
    @NotBlank(
            message = "Confirm password is required"
    )
    private String confirmPass;
}
