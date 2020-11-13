package com.github.deliveries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeestSettingsDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("baseUrl")
    private String baseUrl;

    @NotBlank
    @JsonProperty("token")
    private String token;

    @NotNull
    @JsonProperty("header")
    private Map<String, String> header;

}
