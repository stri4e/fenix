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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailControllers {

    private final SubmitReg submitReg;

    private final ResetPass resetPass;

    @PostMapping("/v1/submit/reg")
    public ResponseEntity<Void> submitReg(@RequestBody ConfirmEmail payload) {
        log.info("Enter: {}", payload);
        this.submitReg.send(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/v1/reset/pass")
    public ResponseEntity<Void> resetPass(@RequestBody ConfirmEmail payload) {
        log.info("Enter: {}", payload);
        this.resetPass.send(payload);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
