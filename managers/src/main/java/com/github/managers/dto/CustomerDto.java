package com.github.managers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 6985662747666560731L;

    @JsonProperty(
            value = "id"
    )
    private Long id;

    @NotBlank
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank
    @JsonProperty(value = "lastName")
    private String lastName;

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

    @NotNull
    @JsonProperty(value = "address")
    private AddressDto address;

}
