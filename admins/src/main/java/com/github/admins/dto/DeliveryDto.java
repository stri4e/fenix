package com.github.admins.dto;

import com.github.admins.payload.DeliveryType;
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
