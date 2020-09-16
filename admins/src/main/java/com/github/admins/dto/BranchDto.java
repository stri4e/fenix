package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "number")
    private Integer number;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "phone")
    private String phone;

}
