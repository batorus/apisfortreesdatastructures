package com.batorus.apisfortreesdatastructures.exception;

public class ElementAlreadyInsertedException extends RuntimeException {
    public ElementAlreadyInsertedException() {
        super();
    }

    public ElementAlreadyInsertedException(String message) {
        super(message);
    }

    public ElementAlreadyInsertedException(String message, Throwable cause) {
        super(message, cause);
    }
}
