package com.microservices.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class RequestTrackingFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestTrackingFilter.class);
    private FilterUtility filterUtility;
    public RequestTrackingFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }
    /**
     * Process the Web request and (optionally) delegate to the next {@code GatewayFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerWebExchange modifiedServerWebExchange = filterUtility.getHeaderTokenUtility(exchange);
        String correlationId = modifiedServerWebExchange.getRequest().getHeaders().getFirst(FilterUtility.correlationId);
        logger.debug("Request moving to next filter with Correlation ID: {}", correlationId);
        return chain.filter(modifiedServerWebExchange)
                .then(Mono.fromRunnable(() -> {
                    logger.debug("Updating response header with the Correlation ID: {}", correlationId);
                    modifiedServerWebExchange.getResponse().getHeaders().set(FilterUtility.correlationId, correlationId);
                }));
    }
}