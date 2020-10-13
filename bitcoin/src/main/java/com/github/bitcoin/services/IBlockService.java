package com.github.bitcoin.services;

import com.github.bitcoin.entity.Block;
import com.github.bitcoin.entity.EntityStatus;

public interface IBlockService {

    Block readByStatus(EntityStatus status);

    void update(Long number);

}
