package com.nachogl1.paramx.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ParamxApiException.class)
    public ResponseEntity<Object> handleGenericException(ParamxApiException exception) {
        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .status(exception.getHttpStatus())
                .statusCode(exception.getHttpStatus().value())
                .timestamp(exception.getTimestamp())
                .build(), exception.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleGenericException(ConstraintViolationException exception) {
        return new ResponseEntity<>(ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(Instant.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Getter
    @Builder
    static class ExceptionResponse {
        private final String message;
        private final HttpStatus status;
        private final int statusCode;
        private final Instant timestamp;
    }
}
