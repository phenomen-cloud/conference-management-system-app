package com.vironit.conferencemanagmentsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ConferenceServiceException extends RuntimeException{
    public ConferenceServiceException() {
        super();
    }

    public ConferenceServiceException(String message) {
        super(message);
    }

    public ConferenceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConferenceServiceException(Throwable cause) {
        super(cause);
    }

    protected ConferenceServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
