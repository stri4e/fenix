package com.github.users.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -817092709984965927L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank
    @JsonProperty(value = "patronymic")
    private String patronymic;

    @NotBlank
    @JsonProperty(value = "dateOfBirth")
    private String dateOfBirth;

    @NotNull
    @JsonProperty(value = "sex")
    private String sex = "unknown";

    public static ProfileDto profileDef(String firstName, String lastName) {
        return new ProfileDto(
                null,
                firstName,
                lastName,
                "default",
                "default",
                "unknown"
        );
    }
}
