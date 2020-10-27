package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 5693842243570892367L;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @JsonProperty(value = "fullName")
    private String fullName;

    @NotBlank
    @JsonProperty(value = "addressRegex")
    private String addressRegex;

    @NotNull
    @JsonProperty(value = "pow")
    private Integer pow;

}
