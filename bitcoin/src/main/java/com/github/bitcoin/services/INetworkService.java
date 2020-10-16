package com.github.bitcoin.services;

import com.github.wrapper.bitcoin.model.NewBlock;

public interface INetworkService {

    Long findLastHeight(String url);

    void handlerBlock(NewBlock block);

}
