package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.admins.payload.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -6916839934598045795L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "type")
    private DeliveryType type;

    @JsonProperty(value = "companyName")
    private String companyName;

    @JsonProperty(value = "branchAddress")
    private String branchAddress;

}
