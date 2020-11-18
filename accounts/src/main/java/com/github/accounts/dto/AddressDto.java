package com.github.accounts.dto;

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
public class AddressDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -8770931954232001036L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "country")
    private String country;

    @NotBlank
    @JsonProperty(value = "city")
    private String city;

    @NotBlank
    @JsonProperty(value = "street")
    private String street;

    @NotNull
    @JsonProperty(value = "streetNumber")
    private Integer streetNumber;

    @JsonProperty(value = "flatNumber")
    private Integer flatNumber;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "zipCode")
    private Integer zipCode;

}
