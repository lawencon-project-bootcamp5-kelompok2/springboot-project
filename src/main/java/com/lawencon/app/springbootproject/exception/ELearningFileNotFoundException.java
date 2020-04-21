package com.lawencon.app.springbootproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ELearningFileNotFoundException extends RuntimeException {
    public ELearningFileNotFoundException(String message) {
        super(message);
    }

    public ELearningFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
