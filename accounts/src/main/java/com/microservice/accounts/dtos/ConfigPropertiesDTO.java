package com.microservice.accounts.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class ConfigPropertiesDTO {
    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;
}