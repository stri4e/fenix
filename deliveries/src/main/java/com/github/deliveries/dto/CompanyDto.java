package com.github.deliveries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 617864927390883530L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

}
