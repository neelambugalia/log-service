package com.fable.logservice.exception;

public class LogNotFoundException extends RuntimeException {

    public LogNotFoundException() {
        super();
    }

    public LogNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LogNotFoundException(final String message) {
        super(message);
    }

    public LogNotFoundException(final Throwable cause) {
        super(cause);
    }
}