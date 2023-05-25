package com.example.retailrewardprogram.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        // Log the error
        logger.error("Resource not found: {}", e.getMessage());
        // Return the error response with appropriate HTTP status
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherException(Exception e) {
        // Log the error
        logger.error("Internal server error: {}", e.getMessage(), e);
        // Return the error response with appropriate HTTP status
        return new ResponseEntity<>("Exception", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
