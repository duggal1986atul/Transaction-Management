package com.converter.currency.TransactionManagement.exception;

public class PurchaseTransactionNotFoundException extends Exception {
    public PurchaseTransactionNotFoundException() {}

    public PurchaseTransactionNotFoundException(String message) {
        super(message);
    }

    public PurchaseTransactionNotFoundException(Throwable cause) {
        super (cause);
    }

    public PurchaseTransactionNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
