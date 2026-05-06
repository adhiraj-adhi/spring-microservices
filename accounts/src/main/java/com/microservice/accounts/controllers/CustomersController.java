package com.microservice.accounts.controllers;

import com.microservice.accounts.dtos.CustomerDetailsDto;
import com.microservice.accounts.dtos.ErrorResponseDto;
import com.microservice.accounts.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "REST APIs for Customer Details in XYZ Bank",
        description = "REST APIs in XYZ Bank for Complete Customer Details"
)
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CustomersController {
    private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);
    private ICustomerService iCustomerService;

    @Operation(
            summary = "Fetch Customer Complete Details REST API",
            description = "REST API to fetch Customer Complete details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse( // when there is internal server error
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader(name = "xyz_bank_correlation_id") String correlationIdToken,
            @RequestParam
            @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must be 10 digits")
            String mobileNumber) {
        logger.debug("Token with Correlation ID xyz_bank_correlation_id: {}", correlationIdToken);
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCompleteCustomerDetails(correlationIdToken, mobileNumber);
        return ResponseEntity.ok(customerDetailsDto);
    }
}
