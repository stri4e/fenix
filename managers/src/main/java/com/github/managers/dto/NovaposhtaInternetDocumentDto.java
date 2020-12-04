package com.github.managers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovaposhtaInternetDocumentDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -3990012416043622915L;

    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("ref")
    private String ref;

    @NotNull
    @JsonProperty("costOnSize")
    private Integer costOnSize;

    @NotBlank
    @JsonProperty("estimatedDeliveryDate")
    private String estimatedDeliveryDate;

    @NotBlank
    @JsonProperty("intDocNumber")
    private String intDocNumber;

    @NotBlank
    @JsonProperty("typeDocument")
    private String typeDocument;

}
