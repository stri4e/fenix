package com.github.deliveries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovaposhtaSettingsDto {

    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("baseUrl")
    private String baseUrl;

    @NotBlank
    @JsonProperty("apiKey")
    private String apiKey;

    @NotNull
    @JsonProperty("headers")
    private Map<String, String> headers;

}
