package com.btcapi.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gateway/tx")
public class BtcTransaction {

    @PostMapping("/btc/create")
    ResponseEntity createTransaction() {
        return null;
    }

    @PostMapping("/btc/sign")
    ResponseEntity signTransaction() {
        return null;
    }
}
