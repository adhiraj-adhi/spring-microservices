package com.microservices.loans.controllers;

import com.microservices.loans.dtos.ConfigPropertiesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Value("${build.version}")
    private String buildInfo;
    private ConfigPropertiesDTO configPropertiesDTO;
    public TestController(ConfigPropertiesDTO configPropertiesDTO) {
        this.configPropertiesDTO = configPropertiesDTO;
    }

    @GetMapping("/build-info")
    public String contactInfo() {
        return buildInfo;
    }

    @GetMapping("/contact-details")
    public ConfigPropertiesDTO getContactDetails() {
        return configPropertiesDTO;
    }
}
