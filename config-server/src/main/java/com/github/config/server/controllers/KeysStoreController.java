package com.github.config.server.controllers;

import com.github.config.server.service.KeysStoreService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/keys")
public class KeysStoreController {

    private final KeysStoreService keysStoreService;

    public KeysStoreController(KeysStoreService keysStoreService) {
        this.keysStoreService = keysStoreService;
    }

    @PostMapping(path = "/{profile}")
    public void generateKeys(@PathVariable(name = "profile") String profile) {
        this.keysStoreService.newKeysStore(profile);
    }

}
