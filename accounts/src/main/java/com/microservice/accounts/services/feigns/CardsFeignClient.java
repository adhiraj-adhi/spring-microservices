package com.microservice.accounts.services.feigns;

import com.microservice.accounts.dtos.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards")
public interface CardsFeignClient {
    @GetMapping(value = "/api/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(
            @RequestHeader("xyz_bank_correlation_id") String correlationIdToken,
            @RequestParam String mobileNumber);
}
