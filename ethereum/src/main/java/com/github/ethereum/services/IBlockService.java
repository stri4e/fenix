package com.github.ethereum.services;

import com.github.ethereum.entity.Block;
import com.github.ethereum.entity.EntityStatus;

public interface IBlockService {

    Block create(Block block);

    Block createDefault();

    Block readByStatus(EntityStatus status);

    void update(Long number, EntityStatus status);

}
