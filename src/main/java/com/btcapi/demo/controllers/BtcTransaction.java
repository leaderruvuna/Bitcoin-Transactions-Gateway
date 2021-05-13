package com.btcapi.demo.controllers;

import com.btcapi.demo.modals.TransactionsModal;
import com.btcapi.demo.services.BtcTransactionService;
import com.btcapi.demo.utils.validators.BtcTransactionInputsValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/gateway/transaction")
public class BtcTransaction {

    private BtcTransactionService btcTransactionService;
    private BtcTransactionInputsValidator btcTransactionInputsValidator;

    public BtcTransaction(BtcTransactionService btcTransactionService, BtcTransactionInputsValidator btcTransactionInputsValidator) {
        this.btcTransactionService = btcTransactionService;
        this.btcTransactionInputsValidator = btcTransactionInputsValidator;
    }

    @Async
    @PostMapping("/btc/create")
    ResponseEntity createTransaction(@RequestBody TransactionsModal transactionsModal) throws IOException {
        String inputAddress = transactionsModal.getInputAddress();
        String outputAddress = transactionsModal.getOutputAddress();
        double amount = transactionsModal.getAmount();
        if (btcTransactionInputsValidator.isTransInputsCreation(inputAddress, outputAddress, amount)) {
            ResponseEntity transData = (ResponseEntity) btcTransactionService.txCreation(inputAddress, outputAddress, amount);
            return new ResponseEntity(transData, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("All fields are required!", HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/sign")
    ResponseEntity signTransaction(String transaction, String toSign, String signatures, String pubKeys) throws IOException {
        if (btcTransactionInputsValidator.isTransInputsSign(transaction, toSign, signatures, pubKeys)) {
            ResponseEntity transSignData = (ResponseEntity) btcTransactionService.txSignHx(transaction, toSign, signatures, pubKeys);
            return new ResponseEntity(transSignData, HttpStatus.OK);
        } else {
            return new ResponseEntity("All fields are required!", HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/push")
    ResponseEntity pushTransaction(String transaction) throws IOException {
        if (btcTransactionInputsValidator.isTransInputsPush(transaction)) {
            ResponseEntity transPushData = (ResponseEntity) btcTransactionService.txPush(transaction);
            return new ResponseEntity(transPushData, HttpStatus.OK);
        } else {
            return new ResponseEntity("All fields are required!", HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/decode")
    ResponseEntity decodeTransaction(String transaction) throws IOException {
        if (btcTransactionInputsValidator.isTransInputsDecode(transaction)) {
            ResponseEntity transDecodeData = (ResponseEntity) btcTransactionService.txDecode(transaction);
            return new ResponseEntity(transDecodeData, HttpStatus.OK);
        } else {
            return new ResponseEntity("All fields are required!", HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/witness")
    ResponseEntity witnessTransaction(String transaction) throws IOException {
        if (btcTransactionInputsValidator.isTransInputsWitness(transaction)) {
            ResponseEntity transWitnessData = (ResponseEntity) btcTransactionService.txWitness(transaction);
            return new ResponseEntity(transWitnessData, HttpStatus.OK);
        } else {
            return new ResponseEntity("All fields are required!", HttpStatus.FORBIDDEN);
        }
    }

    @Async
    @PostMapping("/btc/propagate")
    ResponseEntity propagateTransaction(String transaction) throws IOException {
        if (btcTransactionInputsValidator.isTransInputsPropagate(transaction)) {
            ResponseEntity transWitnessData = (ResponseEntity) btcTransactionService.txPropagation(transaction);
            return new ResponseEntity(transWitnessData, HttpStatus.OK);
        } else {
            return new ResponseEntity("All fields are required!", HttpStatus.FORBIDDEN);
        }
    }
}
