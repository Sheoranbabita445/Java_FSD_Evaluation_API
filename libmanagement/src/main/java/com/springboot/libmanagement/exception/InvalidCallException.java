package com.springboot.libmanagement.exception;

public class InvalidCallException extends RuntimeException{
    public InvalidCallException(String message){
        super(message);
    }
}