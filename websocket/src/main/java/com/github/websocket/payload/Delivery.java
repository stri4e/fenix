package com.github.websocket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery implements Serializable, Cloneable {

    private static final long serialVersionUID = 1655627397181362589L;

    private Long id;

    private DeliveryType type;

    private String companyName;

    private String branchAddress;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Delivery(Long id, DeliveryType type, String companyName, String branchAddress) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
        this.branchAddress = branchAddress;
    }

}
