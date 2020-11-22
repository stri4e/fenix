package com.github.websocket.dto;

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

    private static final long serialVersionUID = -6045330382723097811L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "country")
    private String country;

    @NotBlank
    @JsonProperty(value = "region")
    private String region;

    @NotBlank
    @JsonProperty(value = "city")
    private String city;

    @NotBlank
    @JsonProperty(value = "street")
    private String street;

    @NotBlank
    @JsonProperty(value = "streetNumber")
    private String streetNumber;

    @NotBlank
    @JsonProperty(value = "streetNumber")
    private String flatNumber;

    @NotBlank
    @JsonProperty(value = "zipCode")
    private String zipCode;

}
