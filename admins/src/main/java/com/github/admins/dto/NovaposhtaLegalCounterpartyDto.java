package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovaposhtaLegalCounterpartyDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 481937448914300262L;

    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("ref")
    private String ref;

    @NotBlank
    @JsonProperty("description")
    private String description;

    @NotBlank
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank
    @JsonProperty("middleName")
    private String middleName;

    @NotBlank
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank
    @JsonProperty("counterparty")
    private String counterparty;

    @NotBlank
    @JsonProperty("ownershipForm")
    private String ownershipForm;

    @NotBlank
    @JsonProperty("edrpou")
    private String edrpou;

    @NotBlank
    @JsonProperty("counterpartyType")
    private String counterpartyType;

}
