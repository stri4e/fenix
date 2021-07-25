package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class StockStaff {

    private String firstName;

    private String lastName;

    private String email;

    private List<String> phone;

}
