package com.car.factory.carfactory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.car.factory.carfactory.model.CarDto;
import com.car.factory.carfactory.model.CarHood;

@Component
public class CarHoodClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarHoodClient.class);
	
	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;
	
	public CarHoodClient(@Qualifier("restTemplate") RestTemplate restTemplate,
			DiscoveryClient discoveryClient) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}
	
	
	public ResponseEntity<CarHood> createHood(CarDto carDto) {
		
		ServiceInstance instance = discoveryClient.getInstances("proxy-gateway")
				.stream().findAny()
				.orElseThrow(() -> new IllegalStateException("Proxy unavailable"));
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl(instance.getUri().toString()+"/car-hood/application/createHood")
				.queryParam("brand", carDto.getBrand());
		
//		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
//				.fromHttpUrl("http://car-hood/car-hood/createHood")
//				.queryParam("brand", carDto.getBrand()); // directly consume service without proxy
		
		return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), CarHood.class);
	}
	
}
