package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse implements Serializable {

    private static final long serialVersionUID = -3173306727261701846L;

    private String email;

    private String pass;

}
