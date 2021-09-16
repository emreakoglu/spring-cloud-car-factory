package com.car.factory.carfactoryemployees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.car.factory")
public class CarFactoryEmployees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(CarFactoryEmployees.class, args);

	}

}
