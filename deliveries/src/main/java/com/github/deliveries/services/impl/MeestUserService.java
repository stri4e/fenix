package com.github.deliveries.services.impl;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.MeestUser;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.MeestUserRepo;
import com.github.deliveries.services.IMeestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeestUserService implements IMeestUserService {

    private final MeestUserRepo meestUserRepo;

    @Override
    public void create(MeestUser meestUser) {
        this.meestUserRepo.save(meestUser);
    }

    @Override
    public MeestUser read() {
        return this.meestUserRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String login, String pass) {
        this.meestUserRepo.update(id, login, pass);
    }

    @Override
    public void remove(Long id) {
        this.meestUserRepo.delete(id, EntityStatus.off);
    }

}
