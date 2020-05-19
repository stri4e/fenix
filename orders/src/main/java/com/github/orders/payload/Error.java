package com.github.orders.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    @JsonProperty(value = "status")
    private int status;

    @JsonProperty(value = "message")
    private String message;

}
