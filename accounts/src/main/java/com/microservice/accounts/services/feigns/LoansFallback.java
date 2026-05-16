package com.microservice.accounts.services.feigns;

import com.microservice.accounts.dtos.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class LoansFallback implements LoansFeignClient {
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber) {
        return null;
    }
}