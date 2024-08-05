package com.skysoft.krd.collegemanagementapi.exceptions;

public class RuntimeConflictException extends  RuntimeException{
    public RuntimeConflictException() {
    }

    public RuntimeConflictException(String message) {
        super(message);
    }
}
