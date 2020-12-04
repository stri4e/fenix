package com.github.statistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 3647384241828366594L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "token")
    @NotNull(message = "Required field not null")
    private String token;

    @JsonProperty(value = "ip")
    @NotBlank(message = "Required field not blank")
    private String ip;

    @JsonProperty(value = "information")
    @NotNull(message = "Required field not blank")
    private Map<String, String> information;

}
