package com.github.ethereum.utils;

import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.dto.ContractDto;
import com.github.ethereum.dto.FeeDto;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.*;
import com.github.wrapper.ethrereum.model.TransactionData;

public class TransferObj {

    public static Transaction toIncomingTransaction(TransactionData data, Contract contract) {
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
                TransactionStatus.incoming,
                EntityStatus.on
        );
    }

    public static Transaction toOutgoingTransaction(TransactionData data, Contract contract) {
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
                TransactionStatus.outgoing,
                EntityStatus.off
        );
    }

    public static TransactionDto fromTransaction(Transaction data) {
        return new TransactionDto(
                data.getId(),
                data.getHash(),
                data.getBlockHash(),
                data.getBlockNumber(),
                data.getContract().getName(),
                data.getFrom(),
                data.getTo(),
                data.getValue(),
                data.getFee(),
                data.getTrxStatus()
        );
    }

    public static Contract toContract(ContractDto data) {
        return new Contract(
                data.getId(),
                data.getName(),
                data.getAddress()
        );
    }

    public static ContractDto fromContract(Contract data) {
        return new ContractDto(
                data.getId(),
                data.getName(),
                data.getAddress()
        );
    }

    public static AccountDto fromAccount(Account data) {
        return new AccountDto(
                data.getAddress(),
                data.getCurrencyBalance(),
                data.getContractBalance()
        );
    }

    public static FeeDto fromFee(Fee data) {
        return new FeeDto(
                data.getFee(),
                data.getGasPrice()
        );
    }

}
