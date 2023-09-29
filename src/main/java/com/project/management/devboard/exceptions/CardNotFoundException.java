package com.project.management.devboard.exceptions;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(String message){
        super(message);
    }
}
