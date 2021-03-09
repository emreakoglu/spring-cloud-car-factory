package com.car.factory.carfactory.client;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
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

	public CarEngineClient(@LoadBalanced RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public ResponseEntity<CarEngine> createEngine(CarDto carDto) {
		
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl("http://car-engine/application/createEngine")
				.queryParam("brand", carDto.getBrand());
		
		return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), CarEngine.class);
	}

}
