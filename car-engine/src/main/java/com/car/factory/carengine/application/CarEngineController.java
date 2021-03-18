package com.car.factory.carengine.application;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.factory.carengine.model.CarEngine;

@RestController
@RequestMapping("/application")
public class CarEngineController {
	
	
	@GetMapping("/createEngine")
	public ResponseEntity<CarEngine> createEngine(@RequestHeader("Authorization") String token,@RequestParam String brand) {
		
		CarEngine carEngine = new CarEngine();
		carEngine.setForBrand(brand);
		
		switch (brand) {
		case "BMW":
			carEngine.setEngineCapacity(2.0f);
			carEngine.setTork(220);
			break;
		case "Mercedes":
			carEngine.setEngineCapacity(1.8f);
			carEngine.setTork(200);
			break;
		default:
			carEngine.setEngineCapacity(1.4f);
			carEngine.setTork(110);
			break;
		}
		
		return ResponseEntity.ok(carEngine);
		
	}
}
