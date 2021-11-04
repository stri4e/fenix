package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = -7822776482285231836L;

    @Id
    private String id;

    private Profile profile;

    private Contact contact;

    private EmergencyContact emergencyContact;

    private Address address;

    private UUID employeeId;

    private EntityStatus status = EntityStatus.on;

    @CreatedBy
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public Account(Profile profile, Contact contact, EmergencyContact emergencyContact, Address address, UUID employeeId, EntityStatus status) {
        this.profile = profile;
        this.contact = contact;
        this.emergencyContact = emergencyContact;
        this.address = address;
        this.employeeId = employeeId;
        this.status = status;
    }

    public Account(String id, Profile profile, Contact contact, EmergencyContact emergencyContact, Address address) {
        this.id = id;
        this.profile = profile;
        this.contact = contact;
        this.emergencyContact = emergencyContact;
        this.address = address;
    }

    public static Account defaultAccount(Employee employee) {
        return new Account(
                Profile.defaultProfile(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getPatronymic()
                ),
                Contact.defaultContact(),
                EmergencyContact.defaultEmergencyContact(),
                Address.defaultAddress(),
                employee.getId(),
                EntityStatus.off
        );
    }

    public Account employeeId(UUID employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Account status(EntityStatus status) {
        this.status = status;
        return this;
    }

}
