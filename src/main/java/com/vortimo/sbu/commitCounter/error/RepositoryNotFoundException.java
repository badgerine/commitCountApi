package com.vortimo.sbu.commitCounter.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RepositoryNotFoundException extends RuntimeException {
    public RepositoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
