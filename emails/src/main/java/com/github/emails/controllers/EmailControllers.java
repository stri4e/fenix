package com.github.emails.controllers;

import com.github.emails.models.ConfirmEmail;
import com.github.emails.services.ResetPass;
import com.github.emails.services.SubmitReg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class EmailControllers {

    private final SubmitReg submitReg;

    private final ResetPass resetPass;

    @PostMapping("/submit/reg")
    public ResponseEntity<Void> submitReg(@Valid @RequestBody ConfirmEmail payload) {
        log.info("Enter: {}", payload);
        this.submitReg.send(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset/pass")
    public ResponseEntity<Void> resetPass(@Valid @RequestBody ConfirmEmail payload) {
        log.info("Enter: {}", payload);
        this.resetPass.send(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
