package com.videorentalapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exception.
 *
 * @author Oluwatosin Akinyele
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateResourceException extends Exception{

    public DuplicateResourceException(String message) {
        super(message);
    }
}