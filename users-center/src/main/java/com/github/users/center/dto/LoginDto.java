package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @JsonProperty(value = "userId")
    @NotNull(message = "Required field not null")
    private Long userId;

    @JsonProperty(value = "device")
    @NotBlank(message = "Required field not blank")
    private String device;

    @JsonProperty(value = "location")
    @NotBlank(message = "Required field not blank")
    private String location;

}
