package com.github.ethereum.utils;

import com.github.ethereum.entity.Contract;
import com.github.ethereum.entity.Transaction;
import com.github.ethereum.entity.TransactionStatus;
import com.github.wrapper.ethrereum.model.TransactionData;

public class TransferObj {

    public static Transaction toTransaction(TransactionData data, Contract contract) {
        return new Transaction(
                data.getHash(),
                data.getNonce(),
                data.getBlockHash(),
                data.getBlockNumber(),
                data.getGasPrice(),
                data.getGasLimit(),
                contract,
                data.getFrom(),
                data.getTo(),
                data.getValue(),
                data.getFee(),
                TransactionStatus.incoming
        );
    }

}
