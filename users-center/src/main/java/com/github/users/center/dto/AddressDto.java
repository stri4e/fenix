package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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

    public static AddressDto addressDef() {
        return new AddressDto(
                null,
                "unknown",
                "unknown",
                "unknown",
                "unknown",
                "unknown",
                "unknown",
                "unknown"
        );
    }

}
