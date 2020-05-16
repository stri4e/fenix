package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto {

    @JsonProperty(
            value = "id"
    )
    private Long id;

    @NotBlank(
            message = "Customer name is required."
    )
    @JsonProperty(
            value = "customerName"
    )
    private String customerName;

    @NotBlank(
            message = "Customer address is required."
    )
    @JsonProperty(
            value = "customerAddress"
    )
    private String customerAddress;

    @NotBlank(
            message = "Customer email is required."
    )
    @JsonProperty(
            value = "customerEmail"
    )
    private String customerEmail;

    @NotBlank(
            message = "Customer phone is required."
    )
    @JsonProperty(
            value = "customerPhone"
    )
    private String customerPhone;

}
