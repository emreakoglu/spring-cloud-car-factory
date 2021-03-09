package com.car.factory.carengine.model;

public class CarEngine {
	
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
