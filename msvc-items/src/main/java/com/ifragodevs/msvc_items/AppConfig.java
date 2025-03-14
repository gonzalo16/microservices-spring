package com.ifragodevs.msvc_items;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AppConfig {

	@Bean
	Customizer<Resilience4JCircuitBreakerFactory> customizerCircuitBreaker(){
		return (factory) -> factory.configureDefault(id -> {
			return new Resilience4JConfigBuilder(id).circuitBreakerConfig(CircuitBreakerConfig.custom()
					.slidingWindowSize(10)//Evalua las 10 ultimas llamadas
					.failureRateThreshold(50)//Se abre si fallan 5 de las 10, es decir el 50%
					.waitDurationInOpenState(Duration.ofSeconds(10L))
					.slowCallDurationThreshold(Duration.ofSeconds(2L))
					.slowCallRateThreshold(50)
					.build())
					.timeLimiterConfig(TimeLimiterConfig.custom()
										.timeoutDuration(Duration.ofSeconds(4L))
										.build())
					.build();
					
		});
	}
}
