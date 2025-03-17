package com.ifragodevs.msvc_gateway_server.filters;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SampleGlobalFilter implements GlobalFilter{

	private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		LocalDateTime timestamp = LocalDateTime.now();
		LocalTime dateNow = timestamp.toLocalTime();
		logger.info("TIEMPO PRE REQUEST FILTRO GLOBAL: " + dateNow.toString());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			logger.info("TIEMPO POST REQUEST FILTRO GLOBAL: " +dateNow.toString());
		}));
	}
}
