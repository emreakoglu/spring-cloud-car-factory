package com.car.factory.carfactory.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}
	
	private String brand;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private CarEngine carEngine;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private CarHood carHood;
	
	@Transient
	private final UUID uuid;
	
	public Car(String brand, CarEngine engine, CarHood hood, UUID uuid) {
		super();
		this.brand = brand;
		this.carEngine = engine;
		this.carHood = hood;
		this.uuid = uuid;
	}
	public Car() {
		// TODO Auto-generated constructor stub
		this.uuid = UUID.randomUUID();
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	public CarEngine getCarEngine() {
		return carEngine;
	}
	public void setCarEngine(CarEngine carEngine) {
		this.carEngine = carEngine;
	}
	public CarHood getCarHood() {
		return carHood;
	}
	public void setCarHood(CarHood carHood) {
		this.carHood = carHood;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UUID getUuid() {
		return uuid;
	}
	
	

}
