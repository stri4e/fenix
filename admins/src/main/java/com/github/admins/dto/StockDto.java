package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -1888766794324478502L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "number")
    private String number;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "phone")
    private String phone;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "email")
    private String email;

    @NotNull(message = "Field is required.")
    @JsonProperty(value = "staffNames")
    private Set<String> staffNames;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "country")
    private String country;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "region")
    private String region;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "city")
    private  String city;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "street")
    private String street;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "streetNumber")
    private String streetNumber;

    @NotBlank(message = "Field is required.")
    @JsonProperty(value = "zipCode")
    private String zipCode;

}
