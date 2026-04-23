package com.microservice.accounts.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountsDto {
    @NotNull(message = "Account Number can not be null")
//    @Pattern(regexp = "[0-9]{10}", message = "Account number must be 10 digits") - can be used only with String
    @Min(1000000000L)
    @Max(9999999999L)
    // Problem with @Min(1000000000L) is what if we have account number as "0123456789"
    // But as per our implementation, we are only generating value >= 1000000000L
    // So, this will work fine. To avoid such scenario of edge case we could better
    // go with String type for account number and use @Pattern annotation.
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
