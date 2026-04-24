package com.microservice.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name = "CustomerAccounts",
        description = "Schema to hold Customer and Account information"
)
public class CustomerAccountsDto {
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
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Number of the Customer"
    )
    @NotNull(message = "Account Number can not be null")
//    @Pattern(regexp = "[0-9]{10}", message = "Account number must be 10 digits") - can be used only with String
    @Min(1000000000L)
    @Max(9999999999L)
    private Long accountNumber;

    @Schema(
            description = "Account type of the Customer", example = "Savings"
    )
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address of the Customer"
    )
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;

    // Note: Instead of writing all the field again we could have defined two fields as:
    // private CustomerDto customerDto;
    // private AccountsDto accountsDto;
    // But let it be the other way only now.
}
