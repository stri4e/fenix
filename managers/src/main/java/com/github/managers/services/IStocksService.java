package com.github.managers.services;

import com.github.managers.dto.StockDto;
import com.github.managers.services.impl.StocksService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(
        name = "products",
        fallback = StocksService.class,
        contextId = "stocksId"
)
public interface IStocksService {

    @PostMapping(
            path = "/v1/stocks/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    StockDto save(
            @Valid @RequestBody StockDto payload
    );

    @GetMapping(
            path = "/v1/stocks/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<StockDto> findByAll();

    @PutMapping(
            path = "/v1/stocks/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody StockDto payload);

    @DeleteMapping(
            path = "/v1/stocks/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void remove(@PathVariable(name = "id") Long id);

}
