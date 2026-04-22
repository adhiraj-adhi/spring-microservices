package com.microservice.accounts.mapper;

import com.microservice.accounts.dtos.AccountsDto;
import com.microservice.accounts.dtos.CustomerAccountsDto;
import com.microservice.accounts.dtos.CustomerDto;
import com.microservice.accounts.entities.Accounts;
import com.microservice.accounts.entities.Customer;

import java.util.List;

public class CustomerAccountsMapper {
    public static CustomerAccountsDto mapToCustomerAccountsDto(Customer customer, Accounts accounts,
                                                       CustomerAccountsDto customerAccountsDto) {
        customerAccountsDto.setName(customer.getName());
        customerAccountsDto.setEmail(customer.getEmail());
        customerAccountsDto.setMobileNumber(customer.getMobileNumber());
        customerAccountsDto.setAccountNumber(accounts.getAccountNumber());
        customerAccountsDto.setAccountType(accounts.getAccountType());
        customerAccountsDto.setBranchAddress(accounts.getBranchAddress());
        return customerAccountsDto;
    }


    public static AccountsDto mapToAccountsDto(CustomerAccountsDto customerAccountsDto, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(customerAccountsDto.getAccountNumber());
        accountsDto.setAccountType(customerAccountsDto.getAccountType());
        accountsDto.setBranchAddress(customerAccountsDto.getBranchAddress());
        return accountsDto;
    }

    public static CustomerDto mapToCustomerDto(CustomerAccountsDto customerAccountsDto, CustomerDto customerDto) {
        customerDto.setName(customerAccountsDto.getName());
        customerDto.setEmail(customerAccountsDto.getEmail());
        customerDto.setMobileNumber(customerAccountsDto.getMobileNumber());
        return customerDto;
    }
}
