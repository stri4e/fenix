package com.github.ethereum.services.impl;

import com.github.ethereum.entity.Block;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.repository.BlockRepo;
import com.github.ethereum.services.IBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlockService implements IBlockService {

    private static final long DEFAULT_BLOCK_NUMBER = 11008826L;

    private final BlockRepo blockRepo;

    @Override
    public Block create(Block block) {
        return this.blockRepo.save(block);
    }

    @Override
    public Block createDefault() {
        return this.blockRepo.save(new Block(
                DEFAULT_BLOCK_NUMBER, EntityStatus.on
        ));
    }

    @Override
    public Block readByStatus(EntityStatus status) {
        return this.blockRepo.findByStatus(status)
                .orElse(null);
    }

    @Override
    public void update(Long number, EntityStatus status) {
        this.blockRepo.update(number, status);
    }
}
