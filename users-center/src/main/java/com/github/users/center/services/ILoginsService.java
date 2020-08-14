package com.github.users.center.services;

import com.github.users.center.dto.LoginDto;
import com.github.users.center.services.impl.LoginsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "statistics",
        fallback = LoginsService.class
)
public interface ILoginsService {

    @PostMapping(
            path = "/v1/logins/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void createLogin(@RequestBody LoginDto payload);

}
