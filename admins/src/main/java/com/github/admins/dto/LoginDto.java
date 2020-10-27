package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 5407992044101536119L;

    @ApiModelProperty(
            value = "Login id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "createAt")
    private Date createAt;

    @JsonProperty(value = "device")
    private String device;

    @JsonProperty(value = "location")
    private String location;

}
