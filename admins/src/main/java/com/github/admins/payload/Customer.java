package com.github.admins.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable, Cloneable {

    private static final long serialVersionUID = -6968351368477077711L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "customerName")
    private String customerName;

    @NotBlank
    @JsonProperty(value = "customerAddress")
    private String customerAddress;

    @NotBlank
    @JsonProperty(value = "customerEmail")
    private String customerEmail;

    @NotBlank
    @JsonProperty(value = "customerPhone")
    private String customerPhone;

}
