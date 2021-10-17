package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

    private static final long serialVersionUID = -7822776482285231836L;

    private String id;

    private ProfileDto profile;

    private ContactDto contact;

    private EmergencyContactDto emergencyContact;

    private AddressDto address;

}
