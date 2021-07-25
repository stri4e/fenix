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
public class CommentDto implements Serializable {

    private static final long serialVersionUID = 1623792402872314887L;

    @ApiModelProperty(
            value = "Comment id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Comment first name is required.")
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank(message = "Comment last name is required.")
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank(message = "Comment description is required.")
    @ApiModelProperty(
            value = "Comment description",
            example = "This is supper phone."
    )
    @JsonProperty(value = "description")
    private String text;

    public CommentDto(String firstName, String lastName, @NotBlank(message = "Comment description is required.") String text) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.text = text;
    }

}
