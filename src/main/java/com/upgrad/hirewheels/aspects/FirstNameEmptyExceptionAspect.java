package com.upgrad.hirewheels.aspects;

import com.upgrad.hirewheels.exceptions.FirstNameEmptyException;
import com.upgrad.hirewheels.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class FirstNameEmptyExceptionAspect {
    @ExceptionHandler(FirstNameEmptyException.class)
    public ResponseEntity<CustomResponse> handleUserAlreadyExistsException(Exception e) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        CustomResponse customResponse = new CustomResponse(timestamp, e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(customResponse, HttpStatus.ALREADY_REPORTED);
    }
}
