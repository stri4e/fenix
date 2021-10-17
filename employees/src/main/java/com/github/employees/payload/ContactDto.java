package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto implements Serializable {

    private static final long serialVersionUID = -5630927194373190406L;

    private String phone;

    private String extraPhone;

}
