package com.btcapi.demo.utils.validators;

import org.springframework.stereotype.Component;

@Component
public class BtcTransactionInputsValidator {

    public boolean isTransInputsCreation(String fromAddress, String toAddress, long amount) {

        if (fromAddress.isEmpty() || toAddress.isEmpty() || amount == 0) {
            return false;
        }
        return true;

    }

    public boolean isTransInputsSign(String transaction, String toSign, String signatures, String pubKeys) {
        if (transaction.isEmpty() || toSign.isEmpty() || signatures.isEmpty() || pubKeys.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isTransInputsDecode(String transaction) {
        if (transaction.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isTransInputsPush(String transaction) {
        if (transaction.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isTransInputsWitness(String transaction) {
        if (transaction.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isTransInputsPropagate(String transaction) {
        if (transaction.isEmpty()) {
            return false;
        }
        return true;
    }
}
