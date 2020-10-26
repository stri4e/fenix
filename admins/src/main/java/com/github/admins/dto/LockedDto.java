package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockedDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 5075914453502795093L;

    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "isLocked")
    private boolean isLocked;

}
