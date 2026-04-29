package com.microservices.loans.dtos;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

//@Component
@ConfigurationProperties(prefix = "accounts")
public record ConfigPropertiesDTO(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}