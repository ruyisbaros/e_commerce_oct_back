package com.ahmet.e_commerce_oct_back.exceptions;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
