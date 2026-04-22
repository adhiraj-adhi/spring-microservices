package com.microservice.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomerAccountsDto {
    private String name;
    private String email;
    private String mobileNumber;
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
