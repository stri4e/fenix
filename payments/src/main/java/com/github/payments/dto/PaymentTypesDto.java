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
public class PaymentTypesDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -7730440365339581268L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "id")
    private String alias;

}
