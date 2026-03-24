package com.carshowroom.exception;

public class DuplicateCarException extends RuntimeException {

    public DuplicateCarException(String message) {
        super(message);
    }

}
