package com.github.admins.dto;

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
    private Long id;

    @ApiModelProperty(
            value = "Comment name",
            example = "Alex"
    )
    private String name;

    @ApiModelProperty(
            value = "Comment description",
            example = "This is supper phone."
    )
    private String description;

}
