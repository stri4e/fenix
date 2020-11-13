package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.IMeestUserController;
import com.github.deliveries.dto.MeestUserDto;
import com.github.deliveries.entity.MeestUser;
import com.github.deliveries.services.IMeestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.deliveries.utils.TransferObj.toMeestUser;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/user/meest")
public class MeestUserController implements IMeestUserController {

    private final IMeestUserService meestUserService;

    @Override
    public void save(MeestUserDto payload) {
        this.meestUserService.create(toMeestUser(payload));
    }

    @Override
    public void update(MeestUserDto payload) {
        MeestUser user = this.meestUserService.read();
        this.meestUserService.update(
                user.getId(),
                payload.getLogin(),
                payload.getPass()
        );
    }

    @Override
    public void remove(Long id) {
        this.meestUserService.remove(id);
    }

}
