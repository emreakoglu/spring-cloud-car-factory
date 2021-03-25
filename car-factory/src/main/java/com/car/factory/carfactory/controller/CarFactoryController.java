package com.car.factory.carfactory.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.car.factory.carfactory.model.BMW;
import com.car.factory.carfactory.model.Car;
import com.car.factory.carfactory.model.CarDto;
import com.car.factory.carfactory.model.Department;
import com.car.factory.carfactory.model.Employee;
import com.car.factory.carfactory.model.Mercedes;
import com.car.factory.carfactory.model.Reno;
import com.car.factory.carfactory.service.CarFactoryService;
import com.car.factory.carfactory.service.ICarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/application")
@Slf4j
public class CarFactoryController {
	
	Logger logger = LoggerFactory.getLogger(CarFactoryController.class);
	
	private final CarFactoryService carFactoryService;
	
	@Autowired
	HttpSession httpSession;
	
	public CarFactoryController(CarFactoryService carFactoryService) {
		this.carFactoryService = carFactoryService;
	}
	
	@PostMapping("/carCreate")
	public ResponseEntity<Car> carCreate(@RequestHeader("Authorization") String token,@RequestBody CarDto carDto,
			UriComponentsBuilder uriComponentsBuilder) {
		
		httpSession.setAttribute("Authorization", token);
		
		logger.info("Test");
		
		Car newCar = new Car(carDto.getBrand(),null,null,UUID.randomUUID());
		
		Employee engineEmployee = carFactoryService.getEmployeeByDepartment(Department.ENGINE);
		if (engineEmployee != null) {
			newCar.setCarEngine(carFactoryService.createCarEngine(carDto));
		}
		
		Employee hoodEmployee = carFactoryService.getEmployeeByDepartment(Department.HOOD);
		if(hoodEmployee != null) {
			newCar.setCarHood(carFactoryService.createCarHood(carDto));
		}
		
		carFactoryService.saveCar(newCar);
		
		UriComponents uriComponents = uriComponentsBuilder.path("/applications/carCreate").buildAndExpand(newCar.getUuid());
		
		return ResponseEntity.created(uriComponents.toUri()).body(newCar);
	}

}
