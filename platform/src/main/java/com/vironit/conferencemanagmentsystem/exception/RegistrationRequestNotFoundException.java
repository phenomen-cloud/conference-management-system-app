package com.vironit.conferencemanagmentsystem.exception;

public class RegistrationRequestNotFoundException extends RuntimeException {
    public RegistrationRequestNotFoundException() {
        super();
    }

    public RegistrationRequestNotFoundException(String message) {
        super(message);
    }

    public RegistrationRequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationRequestNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RegistrationRequestNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
