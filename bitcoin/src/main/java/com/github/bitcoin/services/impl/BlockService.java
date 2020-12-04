package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.Block;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.repository.BlockRepo;
import com.github.bitcoin.services.IBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlockService implements IBlockService {

    private final BlockRepo blockRepo;

    @Override
    public Block create(Block block) {
        return this.blockRepo.save(block);
    }

    @Override
    public Block readByStatus(EntityStatus status) {
        return this.blockRepo.findByStatus(status);
    }

    @Override
    public void update(Long number) {
        this.blockRepo.update(number, EntityStatus.on);
    }
}
