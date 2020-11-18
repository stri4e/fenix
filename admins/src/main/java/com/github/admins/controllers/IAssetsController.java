package com.github.admins.controllers;

import com.github.admins.dto.AssetDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IAssetsController {

    @GetMapping(
            path = "/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<AssetDto> findAssetsByStatus(
            @PathVariable(name = "status") String status
    );

    @GetMapping(
            path = "/one/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    AssetDto findAsset(@PathVariable(name = "id") Long id);

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    AssetDto save(@Valid @RequestBody AssetDto payload);

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody AssetDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@Valid @PathVariable(name = "id") Long id);

}
