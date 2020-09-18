package com.github.admins.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch implements Serializable, Cloneable {

    private static final long serialVersionUID = 125375057451013087L;

    private Long id;

    private Integer number;

    private String address;

    private String phone;

    private Integer maxWeight;

    private BranchStatus status;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public Branch(Long id, Integer number, String address, String phone) {
        this.id = id;
        this.number = number;
        this.address = address;
        this.phone = phone;
    }

}
