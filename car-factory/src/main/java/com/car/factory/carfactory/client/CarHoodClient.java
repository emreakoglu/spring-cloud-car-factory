package com.car.factory.carfactory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	public CarHoodClient(@LoadBalanced RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public ResponseEntity<CarHood> createHood(CarDto carDto) {
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl("http://car-hood/application/createHood")
				.queryParam("brand", carDto.getBrand());
		
		return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), CarHood.class);
	}
	
}
