package com.github.master.card.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt implements Serializable, Cloneable {

    private static final long serialVersionUID = 3348086126035718179L;

    @NotNull
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @NotBlank
    @JsonProperty(value = "currency")
    private String currency;

    @NotBlank
    @JsonProperty(value = "identifier")
    private String identifier;

    @NotBlank
    @JsonProperty(value = "expYear")
    private String expYear;

    @NotBlank
    @JsonProperty(value = "expMonth")
    private String expMonth;

    @NotBlank
    @JsonProperty(value = "cvc")
    private Integer cvc;

    @NotBlank
    @JsonProperty(value = "accountUri")
    private String accountUri;

    @NotBlank
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank
    @JsonProperty(value = "address")
    private String address;

    @NotBlank
    @JsonProperty(value = "city")
    private String city;

    @NotNull
    @JsonProperty(value = "postCode")
    private Integer postCode;

    @NotBlank
    @JsonProperty(value = "countrySubdivision")
    private String countrySubdivision;

    @NotBlank
    @JsonProperty(value = "country")
    private String country;

}
