package com.microservice.accounts.controllers;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dtos.CustomerAccountsDto;
import com.microservice.accounts.dtos.CustomerDto;
import com.microservice.accounts.dtos.ResponseDto;
import com.microservice.accounts.services.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// produces -> Tells that the Rest APIs in the controller is going to produce response type of JSON
@AllArgsConstructor
@Validated // required for validating simple method parameters (query params, path vars) and for validation groups
public class AccountsController {
    private IAccountsService iAccountsService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerAccountsDto> fetchAccountDetails(@RequestParam
                @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must be 10 digits")
                String mobileNumber) {
        CustomerAccountsDto customerAccountsDto = iAccountsService.fetchCustomerAndAccount(mobileNumber);
        return ResponseEntity.ok(customerAccountsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerAccountDetails(
            @Valid @RequestBody CustomerAccountsDto customerAccountsDto) {
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
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
            @Pattern(regexp = "[0-9]{10}", message = "Mobile Number must be 10 digits")
            String mobileNumber){
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
