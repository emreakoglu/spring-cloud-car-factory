package com.car.factory.carfactory.model;

import java.util.UUID;

import com.car.factory.carfactory.service.ICarService;

public class BMW extends Car implements ICarService {

	public BMW(String brand, CarEngine engine, CarHood hood, UUID uuid) {
		super(brand, engine, hood, uuid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createEngine(CarEngine createdEngineFromService) {
		// TODO Auto-generated method stub
		this.setCarEngine(createdEngineFromService);

	}

	@Override
	public void createHood(CarHood createdHoodFromService) {
		// TODO Auto-generated method stub
		this.setCarHood(createdHoodFromService);
	}

}
