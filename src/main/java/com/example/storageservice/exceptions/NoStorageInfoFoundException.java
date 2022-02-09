package com.example.storageservice.exceptions;

public class NoStorageInfoFoundException extends Throwable {

    private String message;
    public NoStorageInfoFoundException(String message) {
        this.message = message;
    }
}
