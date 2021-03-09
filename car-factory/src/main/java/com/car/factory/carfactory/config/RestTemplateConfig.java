package com.car.factory.carfactory.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.car.factory.carfactory.excluded.CustomLoadBalancerConfiguration;

@Configuration
@LoadBalancerClients({@LoadBalancerClient(value = "car-hood",configuration = CustomLoadBalancerConfiguration.class),
						@LoadBalancerClient(value = "car-engine",configuration = CustomLoadBalancerConfiguration.class)})
public class RestTemplateConfig {
	
	@Bean
	@LoadBalanced
	RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Qualifier("restTemplate")
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
