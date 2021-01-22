package com.vironit.conferencemanagmentsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistrationServiceException extends RuntimeException{
    public RegistrationServiceException() {
        super();
    }

    public RegistrationServiceException(String message) {
        super(message);
    }

    public RegistrationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationServiceException(Throwable cause) {
        super(cause);
    }

    protected RegistrationServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
