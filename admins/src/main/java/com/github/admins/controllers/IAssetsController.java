package com.github.admins.controllers;

import com.github.admins.dto.AssetDto;
import com.github.admins.payload.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IAssetsController {

    @GetMapping(
            path = "/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<AssetDto> findAssetsByStatus(
            @PathVariable(name = "status") EntityStatus status
    );

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    AssetDto findAsset(@PathVariable(name = "id") Long id);

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    AssetDto save(@RequestBody AssetDto payload);

    @PutMapping(
            path = "/"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody AssetDto payload);

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
