package com.microservices.cards;

import com.microservices.cards.dtos.ConfigPropertiesDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {ConfigPropertiesDTO.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "XYZ Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Abhinav",
						email = "adhi@example.com",
						url = "Contact-URL"
				),
				license = @License(
						name = "Apache 2.0",
						url = "License-URL"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "XYZ Cards microservice REST API Documentation",
				url = "External Doc URL"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
