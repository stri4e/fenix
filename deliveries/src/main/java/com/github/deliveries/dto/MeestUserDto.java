package com.github.deliveries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeestUserDto {

    @NotNull
    @JsonProperty("login")
    private String login;

    @NotNull
    @JsonProperty("pass")
    private String pass;

}
