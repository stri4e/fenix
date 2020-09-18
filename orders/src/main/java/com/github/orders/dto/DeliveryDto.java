package com.github.orders.dto;

import com.github.orders.entity.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private Long id;

    private DeliveryType type;

    private String companyName;

    private String branchAddress;

}
