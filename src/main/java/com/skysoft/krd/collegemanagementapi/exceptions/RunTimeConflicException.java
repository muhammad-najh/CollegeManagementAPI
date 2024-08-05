package com.skysoft.krd.collegemanagementapi.exceptions;

public class RunTimeConflicException extends RuntimeException{
    public RunTimeConflicException() {
    }

    public RunTimeConflicException(String message) {
        super(message);
    }
}
