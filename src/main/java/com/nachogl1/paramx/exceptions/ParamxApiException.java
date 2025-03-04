package com.nachogl1.paramx.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Getter
public class ParamxApiException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
    private final Instant timestamp;

    public ParamxApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = Instant.now();
    }


}
