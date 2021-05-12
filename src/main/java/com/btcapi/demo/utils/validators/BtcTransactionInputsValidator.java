package com.btcapi.demo.utils.validators;

import org.springframework.stereotype.Component;

@Component
public class BtcTransactionInputsValidator {

    public boolean isTransInputsCreation() {
        return false;
    }

    public boolean isTransInputsSign() {
        return false;
    }
}
