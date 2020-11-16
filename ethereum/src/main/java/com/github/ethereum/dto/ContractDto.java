package com.github.ethereum.dto;

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
public class ContractDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 3564068861790087316L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @JsonProperty(value = "full_Name")
    private String fullName;

    @NotBlank
    @JsonProperty(value = "address")
    private String address;

    @NotNull
    @JsonProperty(value = "pow")
    private Integer pow;

}
