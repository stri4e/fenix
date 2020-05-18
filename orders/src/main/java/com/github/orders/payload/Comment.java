package com.github.orders.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;

    private String name;

    private String comment;

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

}
