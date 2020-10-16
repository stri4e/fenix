package com.github.bitcoin.controllers;

import com.github.bitcoin.dto.ReceiptDto;
import com.github.bitcoin.dto.TransactionDto;
import com.github.bitcoin.dto.TrialTransactionDto;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.TrialTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ITransactionController {

    @GetMapping(
            path = "/fetch/trials/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<TrialTransactionDto> findTrialByStatus(
            @PathVariable(name = "status") EntityStatus status
    );

    @PostMapping(
            path = "/edit/generate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    TrialTransactionDto generateTransaction(@Valid @RequestBody ReceiptDto payload);

    @PostMapping(
            path = "/edit/send",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void sendTransaction(@Valid @RequestBody TrialTransaction payload);

    @PutMapping(
            path = "/edit/canceled"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void canceledTransaction(@Valid @RequestBody TrialTransaction payload);

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<TransactionDto> findAllByStatus(
            @PathVariable EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "update_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}
