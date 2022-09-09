package com.moysklad.exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String message) {
        super(String.format("%s not found.", message));
    }
}
