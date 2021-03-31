package com.car.factory.carfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@ComponentScan("com.car.factory")
public class CarFactoryApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(CarFactoryApplication.class, args);
	}

}
