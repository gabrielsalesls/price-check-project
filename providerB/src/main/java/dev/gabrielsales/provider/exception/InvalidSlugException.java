package dev.gabrielsales.provider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSlugException extends RuntimeException {
    public InvalidSlugException(String message) {
        super(message);
    }

}
