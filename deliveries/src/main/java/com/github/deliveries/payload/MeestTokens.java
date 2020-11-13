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
        "token",
        "refresh_token",
        "expiresIn"
})
public class MeestTokens {

    @NotBlank
    @JsonProperty("token")
    private String token;

    @NotBlank
    @JsonProperty("refresh_token")
    private String refreshToken;

    @NotNull
    @JsonProperty("expiresIn")
    private Long expiresIn;

}
