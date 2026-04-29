package com.microservices.loans;

import com.microservices.loans.dtos.ConfigPropertiesDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
//@EnableConfigurationProperties(value = {ConfigPropertiesDTO.class}) // not needed as we are using class instead of record
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "XYZ Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Abhinav",
						email = "adhi@example.com",
						url = "Contact-URL"
				),
				license = @License(
						name = "Apache 2.0",
						url = "URL-For-Licence"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "XYZ Loans microservice REST API Documentation",
				url = "External-Doc-URL"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
