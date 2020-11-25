package com.github.websocket.dto;

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

    private static final long serialVersionUID = 1313611656886200960L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank
    @JsonProperty(value = "customerEmail")
    private String customerEmail;

    @NotBlank
    @JsonProperty(value = "customerPhone")
    private String customerPhone;

    @NotNull
    @JsonProperty(value = "customerAddress")
    private AddressDto customerAddress;

}
