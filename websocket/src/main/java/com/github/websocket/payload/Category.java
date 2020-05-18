package com.github.websocket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;

    @NotBlank(message = "Category name is required")
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
