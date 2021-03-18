package com.car.factory.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayProxyApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(GatewayProxyApplication.class, args);

	}

}
