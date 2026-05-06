package com.microservices.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoutes {
    @Bean
    public RouteLocator routeLocatorBean(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("cards-service", p -> p
                        .order(-1)
                        .path("/xyzbank/cards/api/**")
                        .filters(f -> f
                                .stripPrefix(2)
                                .circuitBreaker(config -> config
                                        .setName("cardsCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport"))
                        )
                        .uri("lb://CARDS")
                )
                .route("loans-service", p -> p
                        .order(-1)
                        .path("/xyzbank/loans/api/**")
                        .filters(f -> f
                                .rewritePath("/xyzbank/loans/(?<segment>.*)", "/${segment}")
                                .circuitBreaker(config -> config
                                        .setName("loansCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport"))
                        )
                        .uri("lb://LOANS")
                ).build();
    }
}
