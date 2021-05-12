package com.btcapi.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.io.IOException;

@Component
public class BtcTransactionService {

    private RestTemplate restTemplate;
    private HttpClient httpClient;
    private final Environment environment;
    private static String blockCypherMainTrans;
    private static String blockCypherTest3Trans;
    private static String blockCypherTestTrans;
    private static String blockCypherMainSign;
    private static String blockCypherTest3Sign;
    private static String blockCypherTestSign;
    private static String blockCypherTestDecode;
    private static String blockCypherTestPush;
    private static String blockCypherTestWitness;
    private static String blockCypherTestPropagation;

    public BtcTransactionService(Environment environment) {
        this.environment = environment;
    }

    @Bean
    void initEnvVariables() {
        blockCypherMainTrans = environment.getProperty("blockCypher.api.main.transaction");
        blockCypherTest3Trans = environment.getProperty("blockCypher.api.test3.transaction");
        blockCypherTestTrans = environment.getProperty("blockCypher.api.test.transaction");
        blockCypherMainSign = environment.getProperty("blockCypher.api.main.sign");
        blockCypherTest3Sign = environment.getProperty("blockCypher.api.test3.sign");
        blockCypherTestSign = environment.getProperty("blockCypher.api.test.sign");
        blockCypherTestDecode = environment.getProperty("blockCypher.api.test.decode");
        blockCypherTestPush = environment.getProperty("blockCypher.api.test.push");
        blockCypherTestWitness = environment.getProperty("blockCypher.api.test.witness");
        blockCypherTestPropagation = environment.getProperty("blockCypher.api.test.propagation");
        restTemplate = new RestTemplate();
    }

    @Async
    //Create transaction
    public Object txCreation(String inputAddress, String outputAddress, double amount) throws IOException {
        String transactionsReqData =
                "{\"inputs\":" +
                        "[{\"addresses\":[\"" + inputAddress + "\"]}]," +
                        "\"outputs\":[{\"addresses\":[\"" + outputAddress + "\"]," +
                        "\"value\":" + amount + "}]" + "}";
        ResponseEntity<String> response
                = restTemplate.postForEntity(blockCypherTestTrans, transactionsReqData, String.class);
        return response;
    }

    @Async
    //Sign transaction
    public Object txSignHx(String transaction, String toSign, String signatures, String pubKeys) throws IOException {
        String txSignData =
                "{\"tx\":{" + transaction + "},\"toSign\":\"["
                        + toSign + "]\",\"signatures\":\"["
                        + signatures + "]\",\"pubkeys\":\"["
                        + pubKeys + "]\"";
        ResponseEntity<String> response
                = restTemplate.postForEntity(blockCypherTestSign, txSignData, String.class);
        return response;
    }

    @Async
    //Push tx Hex transaction
    public Object txPush(String transaction) throws IOException {
        String txPushData =
                "{\"tx\":" + transaction + "";
        ResponseEntity<String> response
                = restTemplate.postForEntity(blockCypherTestPush, txPushData, String.class);
        return response;
    }

    @Async
    //Decode transaction
    public Object txDecode(String transaction) throws IOException {
        String txPushData =
                "{\"tx\":" + transaction + "";
        ResponseEntity<String> response
                = restTemplate.postForEntity(blockCypherTestDecode, txPushData, String.class);
        return response;
    }

    @Async
    //Witness transaction
    public Object txWitness(String transaction) throws IOException {
        String txPushData =
                "{\"witness_tosign_tx\":" + transaction + "";
        ResponseEntity<String> response
                = restTemplate.postForEntity(blockCypherTestWitness, txPushData, String.class);
        return response;
    }

    @Async
    //Propagate transaction
    public Object txPropagation(String transaction) throws IOException {
        String txPushData =
                "{\"tx\":" + transaction + "";
        ResponseEntity<String> response
                = restTemplate.postForEntity(blockCypherTestPropagation, txPushData, String.class);
        return response;
    }
}
