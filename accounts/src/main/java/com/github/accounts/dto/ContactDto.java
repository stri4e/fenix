package com.github.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7250831355173801808L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "phones")
    private String phones;

    @NotBlank
    @JsonProperty(value = "email")
    private String email;

}
