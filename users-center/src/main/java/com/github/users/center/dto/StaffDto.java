package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 5964261600880342887L;

    @NotBlank(message = "Field is required")
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank(message = "Field is required")
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank(message = "Field is required")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "Field is required")
    @JsonProperty(value = "phone")
    private String phone;

}
