package com.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<Error> handleBookNotFoundException(BookNotFoundException exception) {
        Error error = new Error();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<Error> errors = fieldErrors.stream().map(fieldError -> {
            Error error = new Error();
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            error.setErrorMessage(fieldError.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
