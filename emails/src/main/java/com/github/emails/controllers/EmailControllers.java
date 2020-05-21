package com.github.emails.controllers;

import com.github.emails.models.ConfirmEmail;
import com.github.emails.services.ResetPass;
import com.github.emails.services.SubmitReg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class EmailControllers {

    private final SubmitReg submitReg;

    private final ResetPass resetPass;

    @PostMapping("/submit/reg")
    @ResponseStatus(code = HttpStatus.OK)
    public void submitReg(@Valid @RequestBody ConfirmEmail payload) {
        log.info("Enter: {}", payload);
        this.submitReg.send(payload);
    }

    @PostMapping("/reset/pass")
    @ResponseStatus(code = HttpStatus.OK)
    public void resetPass(@Valid @RequestBody ConfirmEmail payload) {
        log.info("Enter: {}", payload);
        this.resetPass.send(payload);
    }

}
