package com.github.bitcoin.services;

import com.github.wrapper.bitcoin.model.NewBlock;

public interface INetworkService {

    Long findLastHeight();

    void handlerBlock(NewBlock block);

}
