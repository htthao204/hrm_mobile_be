package com.example.hrm.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuth(AuthException e) {

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "message", e.getMessage(),
                        "error", "AUTH_ERROR"
                ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException e) {

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "message", e.getMessage()
                ));
    }
}
