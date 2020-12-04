package com.github.bitcoin.services;

import com.github.facade.bitcoin.models.NewBlock;

public interface INetworkService {

    Long findLastHeight(String url);

    void handlerBlock(NewBlock block);

}
