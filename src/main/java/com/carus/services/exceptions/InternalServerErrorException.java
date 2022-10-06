package com.carus.services.exceptions;

public class InternalServerError extends RuntimeException {

    public InternalServerError(String message) {
        super(message);
    }
}
