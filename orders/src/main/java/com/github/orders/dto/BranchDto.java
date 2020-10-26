package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -7984765978124159184L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "number")
    private Integer number;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "phone")
    private String phone;

}
