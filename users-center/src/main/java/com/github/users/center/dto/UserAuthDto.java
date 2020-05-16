package com.github.users.center.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(
        description = "This model use for auth user on this server.")
public class UserAuthDto {

    @ApiModelProperty(
            value = "User name.",
            example = "Alex"
    )
    @NotBlank(
            message = "User name is required"
    )
    private String userName;

    @ApiModelProperty(
            value = "User password.",
            example = "password"
    )
    @NotBlank(
            message = "User name is required"
    )
    private String pass;

}
