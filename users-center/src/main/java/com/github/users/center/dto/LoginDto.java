package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @JsonProperty(value = "token")
    @NotNull(message = "Required field not null")
    private String token;

    @JsonProperty(value = "ip")
    @NotBlank(message = "Required field not blank")
    private String ip;

    @JsonProperty(value = "information")
    @NotNull(message = "Required field not blank")
    private Map<String, Object> information;


}
