package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockedDto {

    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "isLocked")
    private boolean isLocked;

}
