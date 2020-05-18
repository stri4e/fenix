package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationDto {

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @JsonProperty(value = "description")
    private String description;

}
