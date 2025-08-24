package com.paisaManagement.exception;

import com.paisaManagement.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.InstantiationException;
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

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTokenException(InvalidTokenException ex) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid token",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BankAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handlerBankAlreadyExistsException(BankAlreadyExistsException ex){
        ExceptionResponse response=new ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                "Bank already exist",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsignificantFundException.class)
    public ResponseEntity<ExceptionResponse> handlerInsignificantFundException(InsignificantFundException ex){
        ExceptionResponse response=new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Insignificant Amount",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InstantiationException.class)
    public ResponseEntity<ExceptionResponse> handlerInstantiationException(InstantiationException ex){
        ExceptionResponse response=new ExceptionResponse(
                HttpStatus.ALREADY_REPORTED.value(),
                "Bank already exist",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                "User not found.",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
