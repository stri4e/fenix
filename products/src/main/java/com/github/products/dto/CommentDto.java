package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model use for keep comment.")
public class CommentDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 1623792402872314887L;

    @ApiModelProperty(
            value = "Comment id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Comment name is required.")
    @ApiModelProperty(
            value = "Comment name",
            example = "Alex"
    )
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "Comment description is required.")
    @ApiModelProperty(
            value = "Comment description",
            example = "This is supper phone."
    )
    @JsonProperty(value = "description")
    private String description;

    public CommentDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
