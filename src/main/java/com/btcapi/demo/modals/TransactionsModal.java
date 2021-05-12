package com.btcapi.demo.modals;

import org.springframework.stereotype.Component;

@Component
public class TransactionsModal {

    private String inputAddress;
    private String outputAddress;
    private double amount;
    private String toSign;

    public String getInputAddress() {
        return inputAddress;
    }

    public void setInputAddress(String inputAddress) {
        this.inputAddress = inputAddress;
    }

    public String getOutputAddress() {
        return outputAddress;
    }

    public void setOutputAddress(String outputAddress) {
        this.outputAddress = outputAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getToSign() {
        return toSign;
    }

    public void setToSign(String toSign) {
        this.toSign = toSign;
    }
}
