package com.car.factory.carhood.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.factory.carhood.model.CarHood;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/application")
@Api(value = "Car Hood API Dokumantasyonu")
public class CarHoodController {
	
	@GetMapping("/createHood")
	public ResponseEntity<CarHood> createHood(@RequestParam @ApiParam("Car Hood Web Service Parameter") String brand){
		
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
