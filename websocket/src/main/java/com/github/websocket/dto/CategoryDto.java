package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @JsonProperty(value = "id")
    private Long id;
    @NotBlank(message = "Category name is required")
    @JsonProperty(value = "name")
    private String name;
}
