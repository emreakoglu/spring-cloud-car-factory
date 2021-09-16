package com.car.factory.carfactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.factory.carfactory.client.CarEngineClient;
import com.car.factory.carfactory.client.CarFactoryEmployeeClient;
import com.car.factory.carfactory.client.CarHoodClient;
import com.car.factory.carfactory.model.Car;
import com.car.factory.carfactory.model.CarDto;
import com.car.factory.carfactory.model.CarEngine;
import com.car.factory.carfactory.model.CarHood;
import com.car.factory.carfactory.model.Department;
import com.car.factory.carfactory.model.Employee;
import com.car.factory.carfactory.repository.CarRepository;

@Service
public class CarFactoryService {
	
	private final CarEngineClient carEngineClient;
	private final CarHoodClient carHoodClient;
	private final CarFactoryEmployeeClient carFactoryEmployeeClient;
	
	@Autowired
	CarRepository carRepository;
	
	public CarFactoryService(CarEngineClient carEngineClient, CarHoodClient carHoodClient,CarFactoryEmployeeClient carFactoryEmployeeClient) {
	
		this.carEngineClient = carEngineClient;
		this.carHoodClient = carHoodClient;
		this.carFactoryEmployeeClient = carFactoryEmployeeClient;
	}
	
	public void saveCar(Car car) {
		
		carRepository.saveAndFlush(car);
		
	}
	
	public CarEngine createCarEngine(CarDto carDto) {
		
		CarEngine carEngine = carEngineClient.createEngine(carDto).getBody();
		
		return carEngine;
	}
	
	public CarHood createCarHood(CarDto carDto) {
		
		CarHood carHood = carHoodClient.createHood(carDto).getBody();
		
		return carHood;
	}
	
	public Employee getEmployeeByDepartment(Department department) {
		
		Employee employee = carFactoryEmployeeClient.getEmployeeByDepartment(department);
		return employee;
	}
	

}
