package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -1183268148292900990L;

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "profile")
    private ProfileDto profile;

    @NotNull
    @JsonProperty(value = "contact")
    private ContactDto contact;

    @NotNull
    @JsonProperty(value = "address")
    private AddressDto address;

    public static AccountDto accountDef(String firstName, String lastName, String email) {
        return new AccountDto(
                null,
                ProfileDto.profileDef(firstName, lastName),
                ContactDto.contactDef(email),
                AddressDto.addressDef()
        );
    }

}
