package com.nubank.capitalgain.application.domain.exceptions;

public class IllegalOperationStockException extends RuntimeException {

    public IllegalOperationStockException(String message) {
        super(message);
    }
}
