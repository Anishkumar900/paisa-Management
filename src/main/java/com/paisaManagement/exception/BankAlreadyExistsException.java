package com.paisaManagement.exception;

public class BankAlreadyExistsException extends RuntimeException{
    public BankAlreadyExistsException(String message){
        super(message);
    }
}
