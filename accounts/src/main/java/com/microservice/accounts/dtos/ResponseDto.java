package com.microservice.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

// This DTO will help return the response for any every successful request
@Data @AllArgsConstructor
public class ResponseDto {
    private String statusCode;
    private String statusMessage;
}
