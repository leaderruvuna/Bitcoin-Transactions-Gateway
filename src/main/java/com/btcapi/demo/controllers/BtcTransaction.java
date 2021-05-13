package com.btcapi.demo.controllers;

import com.btcapi.demo.modals.TransactionsModal;
import com.btcapi.demo.services.BtcTransactionService;
import com.btcapi.demo.utils.validators.BtcTransactionInputsValidator;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.btcapi.demo.constants.ResponseMessages.*;

@RestController
@RequestMapping("/api/gateway/transaction")
public class BtcTransaction {

    private BtcTransactionService btcTransactionService;
    private BtcTransactionInputsValidator btcTransactionInputsValidator;
    private JSONParser parser;

    public BtcTransaction(BtcTransactionService btcTransactionService, BtcTransactionInputsValidator btcTransactionInputsValidator) {
        this.btcTransactionService = btcTransactionService;
        this.btcTransactionInputsValidator = btcTransactionInputsValidator;
        this.parser = new JSONParser();
    }

    @Async
    @PostMapping("/btc/create")
    ResponseEntity createTransaction(@RequestBody TransactionsModal transactionsModal) throws IOException, ParseException {
        String inputAddress = transactionsModal.getInputAddress();
        String outputAddress = transactionsModal.getOutputAddress();
        long amount = transactionsModal.getAmount();
        if (btcTransactionInputsValidator.isTransInputsCreation(inputAddress, outputAddress, amount)) {
            ResponseEntity transData = (ResponseEntity) btcTransactionService.txCreation(inputAddress, outputAddress, amount);
            JSONParser parser = new JSONParser();
            JSONObject transDataJson = (JSONObject) parser.parse(String.valueOf(transData.getBody()));
            return new ResponseEntity(transDataJson, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(TRANS_CREATION_REQ_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/sign")
    ResponseEntity signTransaction(String transaction, String toSign, String signatures, String pubKeys) throws IOException, ParseException {
        if (btcTransactionInputsValidator.isTransInputsSign(transaction, toSign, signatures, pubKeys)) {
            ResponseEntity transSignData = (ResponseEntity) btcTransactionService.txSignHx(transaction, toSign, signatures, pubKeys);
            JSONObject transSignDataJson = (JSONObject) parser.parse(String.valueOf(transSignData.getBody()));
            return new ResponseEntity(transSignDataJson, HttpStatus.OK);
        } else {
            return new ResponseEntity(TRANS_SIGN_REQ_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/push")
    ResponseEntity pushTransaction(String transaction) throws IOException, ParseException {
        if (btcTransactionInputsValidator.isTransInputsPush(transaction)) {
            ResponseEntity transPushData = (ResponseEntity) btcTransactionService.txPush(transaction);
            JSONObject transPushDataJson = (JSONObject) parser.parse(String.valueOf(transPushData.getBody()));
            return new ResponseEntity(transPushDataJson, HttpStatus.OK);
        } else {
            return new ResponseEntity(TRANS_PUSH_REQ_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/decode")
    ResponseEntity decodeTransaction(String transaction) throws IOException, ParseException {
        if (btcTransactionInputsValidator.isTransInputsDecode(transaction)) {
            ResponseEntity transDecodeData = (ResponseEntity) btcTransactionService.txDecode(transaction);
            JSONObject transDecodeDataJson = (JSONObject) parser.parse(String.valueOf(transDecodeData.getBody()));
            return new ResponseEntity(transDecodeDataJson, HttpStatus.OK);
        } else {
            return new ResponseEntity(TRANS_DECODE_REQ_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/witness")
    ResponseEntity witnessTransaction(String transaction) throws IOException, ParseException {
        if (btcTransactionInputsValidator.isTransInputsWitness(transaction)) {
            ResponseEntity transWitnessData = (ResponseEntity) btcTransactionService.txWitness(transaction);
            JSONObject transWitnessDataJson = (JSONObject) parser.parse(String.valueOf(transWitnessData.getBody()));
            return new ResponseEntity(transWitnessDataJson, HttpStatus.OK);
        } else {
            return new ResponseEntity(TRANS_WITNESS_REQ_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/propagate")
    ResponseEntity propagateTransaction(String transaction) throws IOException {
        if (btcTransactionInputsValidator.isTransInputsPropagate(transaction)) {
            ResponseEntity transWitnessData = (ResponseEntity) btcTransactionService.txPropagation(transaction);
            return new ResponseEntity(transWitnessData, HttpStatus.OK);
        } else {
            return new ResponseEntity(TRANS_PROP_REQ_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }
}
