package com.car.factory.carfactory.client;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.car.factory.carfactory.model.CarDto;
import com.car.factory.carfactory.model.CarEngine;

@Component
public class CarEngineClient {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CarEngineClient.class);
	
	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;

	public CarEngineClient(@Qualifier("restTemplate") RestTemplate restTemplate,
			DiscoveryClient discoveryClient) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}
	
	public ResponseEntity<CarEngine> createEngine(CarDto carDto) {
		
		ServiceInstance instance = discoveryClient.getInstances("proxy-gateway")
				.stream().findAny()
				.orElseThrow(() -> new IllegalStateException("Proxy unavailable"));
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl(instance.getUri().toString()+"/car-engine/application/createEngine")
				.queryParam("brand", carDto.getBrand());
		
		return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), CarEngine.class);		
	}

}
