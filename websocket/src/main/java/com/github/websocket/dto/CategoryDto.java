package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 8471430686072559147L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Category name is required")
    @JsonProperty(value = "name")
    private String name;

}
