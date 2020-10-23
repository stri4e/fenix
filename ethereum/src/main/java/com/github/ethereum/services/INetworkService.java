package com.github.ethereum.services;

import com.github.facade.ethrereum.model.Information;
import com.github.facade.ethrereum.model.TransactionData;

import java.util.List;

public interface INetworkService {

    Long findBlockNumber();

    void createOrUpdateFee(Information info);

    List<String> contractsAddresses();

    List<String> accountAddresses();

    void incomingTransaction(TransactionData data);

    void outgoingTransaction(TransactionData data);

    void incomingContract(TransactionData data);

    void outgoingContract(TransactionData data);

    void updateBlockNumber(Long number);

}
