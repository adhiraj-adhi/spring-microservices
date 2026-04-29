package com.microservices.loans.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @Value("${accounts.message}")
    private String accountsMessage;

    @GetMapping("/test")
    public String contactInfo() {
        return accountsMessage;
    }
}
