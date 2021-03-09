package com.car.factory.carfactory.service;

import com.car.factory.carfactory.model.CarEngine;
import com.car.factory.carfactory.model.CarHood;

public interface ICarService {
	
	public void createEngine(CarEngine carEngine);
	
	public void createHood(CarHood carHood);

}
