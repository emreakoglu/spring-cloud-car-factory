package com.car.factory.carfactory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class CarEngine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}
	
	private float engineCapacity;
	
	private int tork;
	
	private String forBrand;

	public float getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(float engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public int getTork() {
		return tork;
	}

	public void setTork(int tork) {
		this.tork = tork;
	}
	
	public String getForBrand() {
		return forBrand;
	}
	
	public void setForBrand(String forBrand) {
		this.forBrand = forBrand;
	}
}
