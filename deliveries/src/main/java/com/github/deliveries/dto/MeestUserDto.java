package com.github.deliveries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeestUserDto {

    @NotBlank
    @JsonProperty("login")
    private String login;

    @NotBlank
    @JsonProperty("pass")
    private String pass;

}
