package com.github.bitcoin.services;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.UnspentOut;

public interface IUnspentOutService {

    UnspentOut create(UnspentOut unspentOut);

    UnspentOut readByIndexAndHash(Integer index, String hash);

    UnspentOut update(UnspentOut unspentOut, EntityStatus status);

}
