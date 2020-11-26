package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.users.center.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -6121166962109132925L;

    @JsonProperty(value = "firstName")
    @NotBlank(message = "Required field not blank")
    private String firstName;

    @JsonProperty(value = "lastName")
    @NotBlank(message = "Required field not blank")
    private String lastName;

    @JsonProperty(value = "email")
    @NotBlank(message = "Required field not blank")
    private String email;

    @JsonProperty(value = "phone")
    @NotBlank(message = "Required field not blank")
    private String phone;

    public static ClientDto client(User user) {
        return new ClientDto(
                user.getFName(),
                user.getLName(),
                user.getEmail(),
                user.getPhone()
        );
    }

}
