package com.converter.currency.TransactionManagement.exception;

public class ServerSideException extends Exception {

     public ServerSideException(){
     }
    public ServerSideException(String message) {
        super(message);
    }

    public ServerSideException (Throwable cause) {
        super (cause);
    }

    public ServerSideException (String message, Throwable cause) {
        super (message, cause);
    }

}
