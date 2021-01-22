package com.vironit.conferencemanagmentsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FeedbackServiceException extends RuntimeException{
    public FeedbackServiceException() {
        super();
    }

    public FeedbackServiceException(String message) {
        super(message);
    }

    public FeedbackServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeedbackServiceException(Throwable cause) {
        super(cause);
    }

    protected FeedbackServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
