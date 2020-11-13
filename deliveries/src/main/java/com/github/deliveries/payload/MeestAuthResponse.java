package com.github.deliveries.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "fieldName",
        "message",
        "messageDetails",
        "result"
})
public class MeestAuthResponse {

    @NotBlank
    @JsonProperty("status")
    private String status;

    @NotBlank
    @JsonProperty("fieldName")
    private String fieldName;

    @NotBlank
    @JsonProperty("message")
    private String message;

    @NotBlank
    @JsonProperty("messageDetails")
    private String messageDetails;

    @NotNull
    @JsonProperty("result")
    private MeestTokens result;

}
