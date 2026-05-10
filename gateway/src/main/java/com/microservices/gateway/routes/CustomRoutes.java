package com.microservices.gateway.routes;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoutes {
    private RedisRateLimiter redisRateLimiter;
    private KeyResolver keyResolver;
    public CustomRoutes(RedisRateLimiter redisRateLimiter, KeyResolver keyResolver) {
        this.redisRateLimiter = redisRateLimiter;
        this.keyResolver = keyResolver;
    }
    @Bean
    public RouteLocator routeLocatorBean(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("cards-service", p -> p
                        .order(-1)
                        .path("/xyzbank/cards/api/**")
                        .filters(f -> f
                                .stripPrefix(2)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter)
                                        .setKeyResolver(keyResolver))
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
