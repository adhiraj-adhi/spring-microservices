package com.microservice.accounts.controllers;

import com.microservice.accounts.dtos.ConfigPropertiesDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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

    @RateLimiter(name="buildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public String contactInfo() {
        return buildInfo;
    }

    public String getBuildInfoFallback(Throwable throwable) {
        return "Fallback Build version 1.0";
    }

    @GetMapping("/contact-details")
    public ConfigPropertiesDTO getContactDetails() {
        return configPropertiesDTO;
    }
}
