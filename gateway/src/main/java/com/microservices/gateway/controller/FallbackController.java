package com.microservices.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FallbackController {
    @RequestMapping("/contactSupport")
    public String fallbackResponse() {
        return "An error occurred. Please try after some time or contact the support team.";
    }
}
