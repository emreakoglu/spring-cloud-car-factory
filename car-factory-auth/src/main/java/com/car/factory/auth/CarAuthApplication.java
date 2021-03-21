package com.car.factory.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CarAuthApplication {
	
	public static void main(String[] args) {

        SpringApplication.run(CarAuthApplication.class, args);
    }

}
