package com.vironit.conferencemanagmentsystem.exception;

public class ConferenceNotFoundException extends RuntimeException {
    public ConferenceNotFoundException() {
        super();
    }

    public ConferenceNotFoundException(String message) {
        super(message);
    }

    public ConferenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConferenceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ConferenceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
