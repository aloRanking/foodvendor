package com.aloranking.foodvendor.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("Could not find User "+ id);
    }
}
