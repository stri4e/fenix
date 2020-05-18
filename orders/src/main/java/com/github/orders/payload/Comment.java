package com.github.orders.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @JsonProperty(value = "comment")
    private String comment;

}
