package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class StockStaff implements Serializable {

    private static final long serialVersionUID = 7989403996523495180L;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<String> phone;

}
