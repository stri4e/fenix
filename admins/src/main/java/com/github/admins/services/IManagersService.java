package com.github.admins.services;

import com.github.admins.services.impl.ManagersService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "managers",
        fallback = ManagersService.class,
        contextId = "managersId"
)
public interface IManagersService {



}
