package com.car.factory.carfactory.application;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
import com.car.factory.carfactory.model.Mercedes;
import com.car.factory.carfactory.model.Reno;
import com.car.factory.carfactory.service.CarFactoryService;
import com.car.factory.carfactory.service.ICarService;

@RestController
@RequestMapping("/application")
public class CarFactoryController {
	
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
		
		Car newCar = new Car(carDto.getBrand(),null,null,UUID.randomUUID());
		
//		switch (carDto.getBrand()) {
//		case "BMW":
//			newCar = new BMW(carDto.getBrand(),null,null,UUID.randomUUID());
//			break;
//		case "Mercedes":
//			newCar = new Mercedes(carDto.getBrand(), null, null, UUID.randomUUID());
//			break;
//		case "Reno":
//			newCar = new Reno(carDto.getBrand(), null, null, UUID.randomUUID());
//		default:
//			newCar = new Car();
//			break;
//		}
		
//		try {
//			Thread.sleep(5 * 1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		newCar.setCarEngine(carFactoryService.createCarEngine(carDto));
		
		newCar.setCarHood(carFactoryService.createCarHood(carDto));
		
		carFactoryService.saveCar(newCar);
		
		UriComponents uriComponents = uriComponentsBuilder.path("/applications/carCreate").buildAndExpand(newCar.getUuid());
		
		return ResponseEntity.created(uriComponents.toUri()).body(newCar);
	}

}
