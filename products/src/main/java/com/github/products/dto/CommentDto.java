package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model use for keep comment.")
public class CommentDto {

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
