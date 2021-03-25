package com.car.factory.carfactory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.car.factory.carfactory.model.Department;
import com.car.factory.carfactory.model.Employee;

@Component
public class CarFactoryEmployeeClient {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CarFactoryEmployeeClient.class);
	
	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;

	public CarFactoryEmployeeClient(@Qualifier("restTemplate") RestTemplate restTemplate,
			DiscoveryClient discoveryClient) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}
	
	public Employee getEmployeeByDepartment(Department department) {
		ServiceInstance instance = discoveryClient.getInstances("proxy-gateway")
				.stream().findAny().orElseThrow(() -> new IllegalStateException("Proxy unavailable"));
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl(instance.getUri().toString()+"/car-factory-employees/application/getEmployeeByDepartment")
				.queryParam("department", department);
		
		return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Employee.class).getBody();
	}
}
