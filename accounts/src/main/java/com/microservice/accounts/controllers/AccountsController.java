package com.microservice.accounts.controllers;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dtos.CustomerAccountsDto;
import com.microservice.accounts.dtos.CustomerDto;
import com.microservice.accounts.dtos.ResponseDto;
import com.microservice.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// produces -> Tells that the Rest APIs in the controller is going to produce response type of JSON
@AllArgsConstructor
public class AccountsController {
    private IAccountsService iAccountsService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerAccountsDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerAccountsDto customerAccountsDto = iAccountsService.fetchCustomerAndAccount(mobileNumber);
        return ResponseEntity.ok(customerAccountsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerAccountDetails(
            @RequestBody CustomerAccountsDto customerAccountsDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerAccountsDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }
}
