package com.github.ethereum.controllers.impl;

import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.*;
import com.github.ethereum.payload.Receipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.*;

public class TransactionControllerMocks {

    public static final BigInteger PRIVATE_KEY = new BigInteger("100895495268790251376912975136787819366858501190262293512126098766069993773048");

    public static final BigInteger PUBLIC_KEY = new BigInteger("9401955856600962629311101705393210030457726952314846327504770302893759938843867397057501063903299130607873524813504696500333270490896388350007560789853196");

    public static final String ADDRESS = "0x5f955d59be17d6787600810d3eff3584259734cc";

    public static final String EXP_ADDRESS = "0x65E5BC985b8399B338C3C55ff1e3c048586d50ca";

    public static final UUID userId = UUID.fromString("9da421d7-33e4-4319-9288-0f503bd20c3d");

    public static Receipt receipt() {
        return new Receipt(
                ADDRESS,
                "0x024c604048f3b163c3625f4e37943da490530a7e",
                BigInteger.TEN
        );
    }

    public static Contract defaultContractForSave() {
        return new Contract(
               null,
                "default",
                "default",
                "default",
                0
        );
    }

    public static Contract defaultContract() {
        return new Contract(
                1L,
                "default",
                "default",
                "default",
                0
        );
    }

    public static Contract contractForSave() {
        return new Contract(
                null,
                "ice",
                "ice-cream",
                "0x65E5BC985b8399B338C3C55ff1e3c048586d50ca",
                8
        );
    }

    public static Fee feeForSave() {
        return new Fee(
                BigInteger.TEN,
                BigInteger.TEN
        );
    }

    public static Account accountForSave() {
        return new Account(
                userId,
                PRIVATE_KEY,
                PUBLIC_KEY,
                ADDRESS,
                BigInteger.TEN,
                contractBalance()
        );
    }

    public static Map<String, BigInteger> contractBalance() {
        Map<String, BigInteger> result = new HashMap<>();
        result.put("BNB", BigInteger.TEN);
        result.put("COC", BigInteger.TEN);
        result.put("DOC", BigInteger.TEN);
        return result;
    }

    public static TransactionDto responseTrx() {
        return new TransactionDto(
               1L,
                "0xf81fe611b966bfd81f760595b5363a7bb5fa73533bec812f9a8db7dded0506c4",
                "",
                BigInteger.ZERO,
                "default",
                "0x5f955d59be17d6787600810d3eff3584259734cc",
                "0x024c604048f3b163c3625f4e37943da490530a7e",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static TransactionDto responseContract() {
        return new TransactionDto(
                1L,
                "0x9a0c4f11112e0ea67ef85846f31b250eb9a0935687df8bb5fe3a9a58ab74a48e",
                "0x37ae3982afcb0e7d0df2d2f1e2d729b708a3cd4d09a4c93f7b08553aaea2fa46",
                BigInteger.valueOf(11228914),
                "ice",
                "0x5f955d59be17d6787600810d3eff3584259734cc",
                "0x024c604048f3b163c3625f4e37943da490530a7e",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static TransactionReceipt transactionReceipt() {
        return new TransactionReceipt(
                "0x9a0c4f11112e0ea67ef85846f31b250eb9a0935687df8bb5fe3a9a58ab74a48e",
                "1",
                "0x37ae3982afcb0e7d0df2d2f1e2d729b708a3cd4d09a4c93f7b08553aaea2fa46",
                Numeric.encodeQuantity(BigInteger.valueOf(11228914)),
                "10",
                "10",
                "0x4179aA2ddEbAEE589fB294F210ec26D32644dd33",
                "root",
                null,
                "0x5f955d59be17d6787600810d3eff3584259734cc",
                "0x024c604048f3b163c3625f4e37943da490530a7e",
                new ArrayList<>(),
                "logsBloom",
                "revertReason"
        );
    }


    public static List<TransactionDto> TRANSACTIONS_FOR_EQUALS = List.of(
            transactionOneForEquals(),
            transactionTwoForEquals(),
            transactionThreeForEquals(),
            transactionFourForEquals(),
            transactionFiveForEquals()
    );

    public static TransactionDto transactionOneForEquals() {
        return new TransactionDto(
                1L,
                "0xf81fe611b966bfd81f760595b5363a7bb5fa73533bec812f9a8db7dded0506c4",
                "",
                BigInteger.ZERO,
                "default",
                "0x5f955d59be17d6787600810d3eff3584259734cc",
                "0x024c604048f3b163c3625f4e37943da490530a7e",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static TransactionDto transactionTwoForEquals() {
        return new TransactionDto(
                2L,
                "0x76283b35c301a30f61bdd63b135b7984a11bfc4f51fb8b96511f224d0cb469d3",
                "",
                BigInteger.ZERO,
                "default",
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                "0x981583f28c863e81fa50edf8fb27c4bcc97edad6",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static TransactionDto transactionThreeForEquals() {
        return new TransactionDto(
                3L,
                "0x36ff1b6c5b986a91394ecf97feff29fc4ef89ffe3da18283db2d93c72b0b154f",
                "",
                BigInteger.ZERO,
                "default",
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                "0x9af7c46a4ebdfb1e11b5f94196c288c885f37bcd",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static TransactionDto transactionFourForEquals() {
        return new TransactionDto(
                4L,
                "0x7eafa349124beddfcc7ba3684d10b8d11ea663362f899e2d5b7003e65cf21fcc",
                "",
                BigInteger.ZERO,
                "default",
                "0x90d3e79b1d3cb957c238631cf36c654660b2a165",
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static TransactionDto transactionFiveForEquals() {
        return new TransactionDto(
                5L,
                "0x5b6ece1f27825469a7d4ff496c795c7c455080b67de09eba134c32688cde8e8b",
                "",
                BigInteger.ZERO,
                "default",
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                "0x7abe2a84368af480ffd4beb7676133c30f37dc05",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static List<Transaction> TRANSACTIONS_FOR_SAVE = List.of(
            transactionOneForSave(),
            transactionTwoForSave(),
            transactionThreeForSave(),
            transactionFourForSave(),
            transactionFiveForSave()
    );

    public static Transaction transactionOneForSave() {
        return new Transaction(
                "0xf81fe611b966bfd81f760595b5363a7bb5fa73533bec812f9a8db7dded0506c4",
                BigInteger.ONE,
                "",
                BigInteger.ZERO,
                BigInteger.TEN,
                BigInteger.TEN,
                defaultContract(),
                "0x5f955d59be17d6787600810d3eff3584259734cc",
                "0x024c604048f3b163c3625f4e37943da490530a7e",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static Transaction transactionTwoForSave() {
        return new Transaction(
                "0x76283b35c301a30f61bdd63b135b7984a11bfc4f51fb8b96511f224d0cb469d3",
                BigInteger.ONE,
                "",
                BigInteger.ZERO,
                BigInteger.TEN,
                BigInteger.TEN,
                defaultContract(),
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                "0x981583f28c863e81fa50edf8fb27c4bcc97edad6",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static Transaction transactionThreeForSave() {
        return new Transaction(
                "0x36ff1b6c5b986a91394ecf97feff29fc4ef89ffe3da18283db2d93c72b0b154f",
                BigInteger.ONE,
                "",
                BigInteger.ZERO,
                BigInteger.TEN,
                BigInteger.TEN,
                defaultContract(),
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                "0x9af7c46a4ebdfb1e11b5f94196c288c885f37bcd",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static Transaction transactionFourForSave() {
        return new Transaction(
                "0x7eafa349124beddfcc7ba3684d10b8d11ea663362f899e2d5b7003e65cf21fcc",
                BigInteger.ONE,
                "",
                BigInteger.ZERO,
                BigInteger.TEN,
                BigInteger.TEN,
                defaultContract(),
                "0x90d3e79b1d3cb957c238631cf36c654660b2a165",
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

    public static Transaction transactionFiveForSave() {
        return new Transaction(
                "0x5b6ece1f27825469a7d4ff496c795c7c455080b67de09eba134c32688cde8e8b",
                BigInteger.ONE,
                "",
                BigInteger.ZERO,
                BigInteger.TEN,
                BigInteger.TEN,
                defaultContract(),
                "0x4179aa2ddebaee589fb294f210ec26d32644dd33",
                "0x7abe2a84368af480ffd4beb7676133c30f37dc05",
                BigInteger.TEN,
                BigInteger.TEN,
                TransactionType.outgoing
        );
    }

}
