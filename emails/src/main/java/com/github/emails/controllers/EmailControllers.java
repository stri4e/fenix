package com.github.emails.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailControllers {

    @PostMapping("/v1/submit/reg")
    public ResponseEntity<Void> submitReg() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
