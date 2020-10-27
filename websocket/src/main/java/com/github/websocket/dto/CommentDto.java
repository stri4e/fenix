package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -2623463337274004203L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Comment name is required")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "Comment description is required")
    @JsonProperty(value = "description")
    private String description;

}
