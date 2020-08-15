package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "createAt")
    private Date createAt;

    @JsonProperty(value = "device")
    private String device;

    @JsonProperty(value = "location")
    private String location;

}
