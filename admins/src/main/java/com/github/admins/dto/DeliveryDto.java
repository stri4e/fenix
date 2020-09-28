package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.admins.payload.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "type")
    private DeliveryType type;

    @JsonProperty(value = "companyName")
    private String companyName;

    @JsonProperty(value = "branchAddress")
    private String branchAddress;

}
