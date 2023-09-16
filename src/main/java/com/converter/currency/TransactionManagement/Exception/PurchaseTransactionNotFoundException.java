package com.converter.currency.TransactionManagement.Exception;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException() {}

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException (Throwable cause) {
        super (cause);
    }

    public TransactionNotFoundException (String message, Throwable cause) {
        super (message, cause);
    }
}
