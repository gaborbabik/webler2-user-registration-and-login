package com.csaba79coder.webler2userregistrationandlogin.controller.exception;

import com.csaba79coder.webler2userregistrationandlogin.value.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandling {

    @ExceptionHandler(value = {InputMismatchException.class})
    public ResponseEntity<Object> handleInputMismatchException(InputMismatchException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(ErrorCode.ERROR_CODE_001, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(ErrorCode.ERROR_CODE_002, ex.getMessage()), HttpStatus.FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(ErrorCode.ERROR_CODE_003, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(responseBodyWithMessage(ErrorCode.ERROR_CODE_004, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    private String responseBodyWithMessage(ErrorCode code, String message) {
        return Map.of(code, message).toString();
    }
}
