package com.microservice.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

// We will use this DTO whenever there is an error/exception in request and we want to return the response
@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath; // path for which end-user got error
    @Schema(
            description = "Error code representing the error"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error message in the response"
    )
    private String errorMessage;
    @Schema(
            description = "Time representing the error-time"
    )
    private LocalDateTime errorTime;
}
