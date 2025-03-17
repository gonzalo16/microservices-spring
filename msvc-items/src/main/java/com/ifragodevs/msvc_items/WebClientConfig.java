package com.ifragodevs.msvc_items;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Value("${config.base-url.endpoint.msvc-products}")
	private String msvcProductUrl;
	
	/*@Bean
	@LoadBalanced
	WebClient.Builder webClient(){
		return WebClient.builder().baseUrl(msvcProductUrl);
	}*/
	
	@Bean
	WebClient webClient(WebClient.Builder builder, ReactorLoadBalancerExchangeFilterFunction lbFunction){
		return builder.baseUrl(msvcProductUrl)
				.filter(lbFunction)
				.build();
	}
}
