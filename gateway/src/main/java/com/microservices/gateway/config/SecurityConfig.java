package com.microservices.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(auth -> auth
                // 1. Business Rule: Allow all global GET operations to flow down without authentication
                .pathMatchers(HttpMethod.GET).permitAll()

                // 2. Business Rule: Enforce authentication on state-changing or explicit microservice domains
                .pathMatchers("/xyzbank/accounts/**").authenticated()
                .pathMatchers("/xyzbank/cards/**").authenticated()
                .pathMatchers("/xyzbank/loans/**").authenticated()
        )

        // 3. Convert the gateway into an OAuth2 Resource Server utilizing default JWT rules
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        // The above line tells Spring Security that this application is an OAuth2 Resource
        // Server, and it should authenticate incoming requests using JWT Access Tokens.

        // 4. Disable CSRF (Cross-Site Request Forgery) protections
        // This is a stateless machine-to-machine API using OAuth2 Client Credentials flow,
        // so there is no browser session or user context where CSRF can occur.
        http.csrf(csrfSpec -> csrfSpec.disable());

        return http.build();
    }
}
