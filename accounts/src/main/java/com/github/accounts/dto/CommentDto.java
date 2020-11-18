package com.github.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model use for keep comment.")
public class CommentDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 7235738630711849155L;

    @ApiModelProperty(
            value = "Comment id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Comment name",
            example = "Alex"
    )
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(
            value = "Comment description",
            example = "This is supper phone."
    )
    @JsonProperty(value = "description")
    private String description;

}
