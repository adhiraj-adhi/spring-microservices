package com.microservice.accounts.exception;

import com.microservice.accounts.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice // With this annotation we are telling Spring that whenever any exception
// occurs in our controller, search for the corresponding matching @ExceptionHandler in
// this class.
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistException.class) // this will be matched as handling
    // code when there is CustomerAlreadyExistException.
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(CustomerAlreadyExistException exception,
                                                                                WebRequest webRequest) {
        // we are using WebRequest to get hold of path which we will return as part of ErrorResponseDto

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class) // this will be matched as handling
    // code when there is CustomerAlreadyExistException.
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                                WebRequest webRequest) {
        // we are using WebRequest to get hold of path which we will return as part of ErrorResponseDto

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
