package com.foro.desafio.foro.domain.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DealErrors {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity dealError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity dealError400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();

        return ResponseEntity.badRequest().body(errors);
    }

    private record DataErrorValidation(String field, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
