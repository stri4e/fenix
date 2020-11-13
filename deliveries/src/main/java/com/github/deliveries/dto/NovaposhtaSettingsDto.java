package com.github.deliveries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovaposhtaSettingsDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("baseUrl")
    private String baseUrl;

    @NotNull
    @JsonProperty("apiKey")
    private String apiKey;

    @NotNull
    @JsonProperty("headers")
    private Map<String, String> headers;

}
