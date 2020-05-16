package com.github.users.center.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for get user on this server.")
public class UserDto {

    @ApiModelProperty(
            value = "User id.",
            example = "1"
    )
    @NotNull(
            message = "Id is required"
    )
    private Long id;

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
            example = "Northred"
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
            value = "User enable.",
            example = "true"
    )
    private boolean isEnable;

    @ApiModelProperty(
            value = "User roles."
    )
    @NotEmpty
    private List<String> roles = new ArrayList<>();

}
