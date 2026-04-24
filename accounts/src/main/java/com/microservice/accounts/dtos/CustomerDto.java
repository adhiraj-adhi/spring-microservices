package com.microservice.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the Customer", example = "Adhiraj Adhi"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 3, max = 30, message = "The length of customer name should be between 3 and 30")
    private String name;

    @Schema(
            description = "Email address of the Customer", example = "adhiraj@example.com"
    )
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address is invalid")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer", example = "9876543210"
    )
//    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit long") // This pattern also allows empty value
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must be 10 digits")
    private String mobileNumber;
}
