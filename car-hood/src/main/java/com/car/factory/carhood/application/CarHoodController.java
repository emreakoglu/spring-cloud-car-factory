package com.car.factory.carhood.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.factory.carhood.model.CarHood;

@RestController
@RequestMapping("/application")
public class CarHoodController {
	
	@GetMapping("/createHood")
	public ResponseEntity<CarHood> createHood(@RequestParam String brand){
		
		CarHood carHood = new CarHood();
		carHood.setColor("Red"); // default
		carHood.setBrand(brand);
		
		switch (brand) {
		case "BMW":
			carHood.setWeight(2000);
			break;
		case "Mercedes":
			carHood.setWeight(1700);
		default:
			carHood.setWeight(1500);
			break;
		}
		
		return ResponseEntity.ok(carHood);
	}

}
