package com.microservice.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

// We will use this DTO whenever there is an error/exception in request and we want to return the response
@Data @AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath; // path for which end-user got error
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
