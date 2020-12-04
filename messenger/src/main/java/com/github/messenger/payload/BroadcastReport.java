package com.github.messenger.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastReport implements Serializable, Cloneable {

    private static final long serialVersionUID = -6212423249386935651L;

    @JsonProperty(value = "successPhones")
    @NotEmpty(message = "Field required not empty.")
    private List<String> successPhones;

    @JsonProperty(value = "failurePhones")
    @NotBlank(message = "Field required not blank.")
    private List<String> failurePhones;

    @JsonProperty(value = "text")
    @NotBlank(message = "Field required not blank.")
    private String text;

    @JsonProperty(value = "languageCode")
    @NotBlank(message = "Field required not blank.")
    private String languageCode;

    @JsonProperty(value = "transliteration")
    @NotBlank(message = "Field required not blank.")
    private String transliteration;
}
