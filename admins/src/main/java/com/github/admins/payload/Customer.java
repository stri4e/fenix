package com.github.admins.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable, Cloneable {

    private static final long serialVersionUID = -6968351368477077711L;

    private Long id;

    private String customerName;

    private String customerAddress;

    private String customerEmail;

    private String customerPhone;

    public Customer(String customerName,
                    String customerAddress,
                    String customerEmail,
                    String customerPhone) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

}
