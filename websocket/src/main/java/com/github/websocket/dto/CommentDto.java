package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Comment name is required")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "Comment description is required")
    @JsonProperty(value = "description")
    private String description;

}
