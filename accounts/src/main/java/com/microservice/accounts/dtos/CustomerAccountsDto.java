package com.microservice.accounts.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomerAccountsDto {
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 3, max = 30, message = "The length of customer name should be between 3 and 30")
    private String name;

    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address is invalid")
    private String email;

    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @NotNull(message = "Account Number can not be null")
//    @Pattern(regexp = "[0-9]{10}", message = "Account number must be 10 digits") - can be used only with String
    @Min(1000000000L)
    @Max(9999999999L)
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
