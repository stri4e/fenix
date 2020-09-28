package com.github.admins.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @JsonProperty(value = "id")
    private Long id;

    @NotNull
    @JsonProperty(value = "type")
    private DeliveryType type;

    @NotBlank
    @JsonProperty(value = "companyName")
    private String companyName;

    @NotBlank
    @JsonProperty(value = "branchAddress")
    private String branchAddress;

}
