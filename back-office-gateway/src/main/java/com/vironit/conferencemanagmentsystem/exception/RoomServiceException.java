package com.vironit.conferencemanagmentsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RoomServiceException extends RuntimeException{
    public RoomServiceException() {
        super();
    }

    public RoomServiceException(String message) {
        super(message);
    }

    public RoomServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomServiceException(Throwable cause) {
        super(cause);
    }

    protected RoomServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
