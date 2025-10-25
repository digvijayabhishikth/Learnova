package com.org.learnova.exception;

public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException(String message){
        super(message);
    }
}
