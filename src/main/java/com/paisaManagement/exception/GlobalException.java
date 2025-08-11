package com.paisaManagement.exception;

import com.paisaManagement.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExist(UserAlreadyExistException ex) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                "User already exists",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handle wrapped RuntimeExceptions and check if cause is UserAlreadyExistException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex) {
        if (ex.getCause() instanceof UserAlreadyExistException) {
            return handleUserAlreadyExist((UserAlreadyExistException) ex.getCause());
        }
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
