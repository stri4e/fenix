package com.github.payments.controllers;

import com.github.payments.dto.AssetDto;
import com.github.payments.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IAssetsController {

    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<AssetDto> findAssets();

    @GetMapping(
            path = "/fetch/all/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<AssetDto> findAssetsByStatus(
            @PathVariable(name = "status") EntityStatus status
    );

    @GetMapping(
            path = "/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    AssetDto findAsset(@PathVariable(name = "id") Long id);

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    AssetDto save(@RequestBody AssetDto payload);

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@RequestBody AssetDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteByStatus(@PathVariable(name = "id") Long id);

}
