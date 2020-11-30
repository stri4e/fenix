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
public class CustomerDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -6533009128642214593L;

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
    private String email;

    @NotBlank
    @JsonProperty(value = "customerPhone")
    private String phone;

    @NotNull
    @JsonProperty(value = "customerAddress")
    private AddressDto address;

    public CustomerDto(
            ProfileDto profile,
            ContactDto contact,
            AddressDto customerAddress) {
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.email = contact.getEmail();
        this.phone = contact.getPhone();
        this.address = customerAddress;
    }

}
