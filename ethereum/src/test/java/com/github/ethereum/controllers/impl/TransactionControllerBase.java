package com.github.ethereum.controllers.impl;

import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.*;
import org.springframework.web.bind.annotation.RequestMethod;

public class TransactionControllerBase {

    private final MockServerClient client;

    public TransactionControllerBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void sendTransactionRequest() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/")
                        .withBody(JsonBody.json("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionCount\",\"params\":[\"0x5f955d59be17d6787600810d3eff3584259734cc\",\"pending\"],\"id\":0}")),
                Times.exactly(30)
        ).respond(
                HttpResponse.response()
                        .withBody(JsonBody.json("{\"id\":1, \"jsonrpc\":\"2.0\",\"result\":\"0x5bad55\"}"))
        );
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/")
                        .withBody(JsonBody.json("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendRawTransaction\",\"params\":[\"0xf862835bad550a82520894024c604048f3b163c3625f4e37943da490530a7e0a801ca0e2ad7d1e30298ce2b12a303dee4ce8b7cbccc5b02ad012b062d3ba4382ff1eb4a06e3c8ee6e03a0302b93121f5efad75c2b01c097c871a40fc063517f6f3bc9b0e\"],\"id\":1}")),
                Times.exactly(30)
        ).respond(
                HttpResponse.response()
                        .withBody(JsonBody.json("{\"id\":1, \"jsonrpc\":\"2.0\",\"result\":\"0xf81fe611b966bfd81f760595b5363a7bb5fa73533bec812f9a8db7dded0506c4\"}"))
        );
    }

    //0xa9059cbb000000000000000000000000024c604048f3b163c3625f4e37943da490530a7e000000000000000000000000000000000000000000000000000000000000000a
    //transfer
    public void sendContractRequest() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/")
                        .withBody(JsonBody.json("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionCount\",\"params\":[\"0x5f955d59be17d6787600810d3eff3584259734cc\",\"pending\"],\"id\":2}")),
                Times.exactly(30)
        ).respond(
                HttpResponse.response()
                        .withBody(JsonBody.json("{\"id\":2, \"jsonrpc\":\"2.0\",\"result\":\"0x5bad55\"}"))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/")
                        .withBody(JsonBody.json("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionCount\",\"params\":[\"0x5f955d59be17d6787600810d3eff3584259734cc\",\"pending\"],\"id\":3}")),
                Times.exactly(30)
        ).respond(
                HttpResponse.response()
                        .withBody(JsonBody.json("{\"id\":3, \"jsonrpc\":\"2.0\",\"result\":\"0x5bad55\"}"))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/")
                        .withBody(JsonBody.json("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendRawTransaction\",\"params\":[\"0xf8a7835bad550a8252089465e5bc985b8399b338c3c55ff1e3c048586d50ca80b844a9059cbb000000000000000000000000024c604048f3b163c3625f4e37943da490530a7e000000000000000000000000000000000000000000000000000000000000000a1ca0cd7ffe7ec13535d3d30324e6e9b6212eb2f6935f6b29329fe695ffdf9767c7a9a07b80e7774be1c6fc60c578b5d567d6fef09a6e12e35acd752b3029b470b3a911\"],\"id\":4}")),
                Times.exactly(30)
        ).respond(
                HttpResponse.response()
                        .withBody(JsonBody.json("{\"id\":4, \"jsonrpc\":\"2.0\",\"result\":\"0x9a0c4f11112e0ea67ef85846f31b250eb9a0935687df8bb5fe3a9a58ab74a48e\"}"))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/")
                        .withBody(JsonBody.json("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionReceipt\",\"params\":[\"0x9a0c4f11112e0ea67ef85846f31b250eb9a0935687df8bb5fe3a9a58ab74a48e\"],\"id\":5}")),
                Times.exactly(30)
        ).respond(
                HttpResponse.response()
                        .withBody(JsonBody.json("{\"id\":5, \"jsonrpc\":\"2.0\",\"result\":{\"transactionHash\":\"0x9a0c4f11112e0ea67ef85846f31b250eb9a0935687df8bb5fe3a9a58ab74a48e\",\"transactionIndex\":1,\"blockHash\":\"0x37ae3982afcb0e7d0df2d2f1e2d729b708a3cd4d09a4c93f7b08553aaea2fa46\",\"blockNumber\":11228914,\"cumulativeGasUsed\":10,\"gasUsed\":10,\"contractAddress\":\"0x4179aA2ddEbAEE589fB294F210ec26D32644dd33\",\"root\":\"root\",\"status\":null,\"from\":\"0x5f955d59be17d6787600810d3eff3584259734cc\",\"to\":\"0x024c604048f3b163c3625f4e37943da490530a7e\",\"logs\":[],\"logsBloom\":\"logsBloom\",\"revertReason\":\"revertReason\",\"statusOK\":false,\"transactionIndexRaw\":\"1\",\"blockNumberRaw\":\"0xab56f2\",\"gasUsedRaw\":\"10\",\"cumulativeGasUsedRaw\":\"10\"}}"))
        );

    }

}
