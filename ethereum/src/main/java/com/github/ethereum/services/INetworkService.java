package com.github.ethereum.services;

import com.github.wrapper.ethrereum.model.Information;
import com.github.wrapper.ethrereum.model.TransactionData;

import java.util.List;

public interface INetworkService {

    void createOrUpdateFee(Information info);

    List<String> contractsAddresses();

    List<String> accountAddresses();

    void incomingTransaction(TransactionData data);

    void outgoingTransaction(TransactionData data);

    void incomingContract(TransactionData data);

    void outgoingContract(TransactionData data);

}
