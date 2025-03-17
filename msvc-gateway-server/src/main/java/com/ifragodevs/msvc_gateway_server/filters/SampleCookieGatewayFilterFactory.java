package com.ifragodevs.msvc_gateway_server.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class SampleCookieGatewayFilterFactory extends AbstractGatewayFilterFactory<SampleCookieGatewayFilterFactory.ConfigurationCookie>{

	private final Logger logger = LoggerFactory.getLogger(SampleCookieGatewayFilterFactory.class);
	
	public SampleCookieGatewayFilterFactory() {
		super(ConfigurationCookie.class);
	}
	
	@Override
	public GatewayFilter apply(ConfigurationCookie config) {
		return (exchange,chain) -> {
			
			//Antes de ejecutar la cadena de filtros
			logger.info("Ejecutando de Gateway filter factory: " +config.message);
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				//Despues de ejecutar la cadena de filtros
				Optional.ofNullable(config.value).ifPresent(cookie -> {
					exchange.getResponse().addCookie(ResponseCookie.from(config.name,cookie).build());
				});
				
				logger.info("Ejecutando de Gateway filter factory: " +config.message);
			}));
		};
	}
	
	
	@Data
	public static class ConfigurationCookie{
		
		private String name;
		private String value;
		private String message;
	}

	

}
