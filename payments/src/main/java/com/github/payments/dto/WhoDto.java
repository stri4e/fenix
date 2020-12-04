package com.github.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhoDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7827870418318065445L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "firsName")
    private String firsName;

    @NotBlank
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank
    @JsonProperty(value = "patronymic")
    private String patronymic;

    public WhoDto(@NotBlank String firsName,
                  @NotBlank String lastName,
                  @NotBlank String patronymic) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }
}
