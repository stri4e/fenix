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
public class BroadcastMessage implements Serializable, Cloneable {

    private static final long serialVersionUID = 1889594388033990757L;

    @JsonProperty(value = "phoneNumber")
    @NotEmpty(message = "Field required not empty.")
    private List<String> phoneNumber;

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
