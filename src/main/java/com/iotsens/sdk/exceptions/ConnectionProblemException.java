package com.iotsens.sdk.exceptions;

public class ConnectionProblemException extends RuntimeException {

    public ConnectionProblemException() {
    }

    public ConnectionProblemException(Exception e) {
        super(e);
    }
}
