package com.microservice.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(     // main section users see first in Swagger UI.
				title = "Accounts microservice REST API documentation",
				description = "XYZBank's Accounts microservice REST API documentation",
				version = "v1",
				contact = @Contact(  // Who should someone reach out to?
						name = "Abhinav",
						email = "abhi@gmail.com",
						url = "URL where user can reach out"
				),
				license = @License( // What are people allowed to do with this API (or its definition)?
						name = "Apache 2.0",
						url = "Link to full license text"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Full Documentation of XYZBank's Accounts microservice REST API",
				url = "This is a link to additional documentation like Confluence page, GitHub README, etc."
		)
		// Other details like extensions, security, servers, and tags can also be provided
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
