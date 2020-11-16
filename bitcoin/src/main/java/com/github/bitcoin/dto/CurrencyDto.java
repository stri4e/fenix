package com.github.bitcoin.dto;

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
public class CurrencyDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -7377066527998344106L;

    @JsonProperty(value = "id")
    private Long id;

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
