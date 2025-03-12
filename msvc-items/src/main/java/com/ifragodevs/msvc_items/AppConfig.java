package com.ifragodevs.msvc_items;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class AppConfig {

	@Bean
	Customizer<Resilience4JCircuitBreakerFactory> customizerCircuitBreaker(){
		return (factory) -> factory.configureDefault(id -> {
			return new Resilience4JConfigBuilder(id).circuitBreakerConfig(CircuitBreakerConfig.custom()
					.slidingWindowSize(10)
					.failureRateThreshold(50)
					.waitDurationInOpenState(Duration.ofSeconds(10L))
					.build())
					.build();
					
		});
	}
}
