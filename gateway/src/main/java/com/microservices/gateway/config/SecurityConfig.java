package com.microservices.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(auth -> auth
                // 1. Business Rule: Allow all global GET operations to flow down without authentication
                .pathMatchers(HttpMethod.GET).permitAll()

                // 2. Business Rule: Enforce authentication on state-changing or explicit microservice domains
                .pathMatchers("/xyzbank/accounts/**").hasRole("ACCOUNTS")
                .pathMatchers("/xyzbank/cards/**").hasRole("CARDS")
                .pathMatchers("/xyzbank/loans/**").hasRole("LOANS")
        )

        // 3. Convert the gateway into an OAuth2 Resource Server utilizing default JWT rules
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        // With this line we are saying Spring that when you convert a JWT
                        // into an Authentication object, don’t use the default mapping — use
                        // MY custom logic defined in jwtAuthenticationConverter().
                ));
        // The above line tells Spring Security that this application is an OAuth2 Resource
        // Server, and it should authenticate incoming requests using JWT Access Tokens.

        // 4. Disable CSRF (Cross-Site Request Forgery) protections
        // This is a stateless machine-to-machine API using OAuth2 Client Credentials flow,
        // so there is no browser session or user context where CSRF can occur.
        http.csrf(csrfSpec -> csrfSpec.disable());

        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // ===================== METHOD 1: ==============================
            // Step 1: Extract realm_access
            Map<String, Object> realmAccess =
                    (Map<String, Object>) jwt.getClaims().get("realm_access");
            if (realmAccess == null) { // To avoid NullPointerException
                // return Flux.fromIterable(Collections.emptyList());
                return Flux.empty(); // Is equivalent to return Flux.fromIterable(Collections.emptyList());
            }


            // Step 2: Extract roles
            List<String> strRoles =
                    (List<String>) realmAccess.get("roles");
            if (strRoles == null) { // To avoid NullPointerException
                // return Flux.fromIterable(Collections.emptyList());
                return Flux.empty();
            }

            // Step 3: Convert to Spring Security authorities (with prefix "ROLE_")
            List<SimpleGrantedAuthority> roles = strRoles
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();
            return Flux.fromIterable(roles);


            // ===================== METHOD 2: ==============================
//            List<SimpleGrantedAuthority> roles = ((List<String>) ((Map<?, ?>) jwt.getClaims()
//                    .get("realm_access"))
//                    .get("roles"))
//                    .stream()
//                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                    .toList();
//
//            return Flux.fromIterable(roles);
        });
        return converter;
    }
}