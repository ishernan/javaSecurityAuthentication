package com.aelion.mycrm.exceptions;

public class DisabledUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public DisabledUserException(String error) {
        super(error);
    }

}
