package com.example.storageservice.exceptions;

public class NoStorageFoundException extends Throwable {

    private String message;
    public NoStorageFoundException(String message) {
        this.message = message;
    }
}
