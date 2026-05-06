package com.microservices.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.UUID;

@Component
public class FilterUtility {
    public static final String correlationId = "xyz_bank_correlation_id";
    private static final Logger logger = LoggerFactory.getLogger(FilterUtility.class);
    public ServerWebExchange getHeaderTokenUtility(ServerWebExchange exchange) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String existingToken = httpHeaders.getFirst(correlationId);
        // httpHeaders.getFirst(headerName): Return the first header value for the given header name, if any.
        if (existingToken==null) {
            logger.debug("Generating token as there is no token in header with correlation Id: {}", correlationId);
            String token = generateToken();
            return setTokenInHeader(token, exchange);
        }
        logger.debug("Existing Correlation ID found: {}", existingToken);
        return exchange;
    }

    private ServerWebExchange setTokenInHeader(String token, ServerWebExchange exchange) {
        return exchange.mutate().request(exchange
                .getRequest()
                .mutate().header(correlationId, token).build()).build();
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
