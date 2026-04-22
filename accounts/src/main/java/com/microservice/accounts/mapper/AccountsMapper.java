package com.microservice.accounts.mapper;

import com.microservice.accounts.dtos.AccountsDto;
import com.microservice.accounts.entities.Accounts;

public class AccountsMapper {
    //  We could use ModelMapper to simplify mapping,
    // but manual mapping or MapStruct is often preferred for better performance and control.
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
