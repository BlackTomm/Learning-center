package com.spring.bean;

class Car {

	public Car() {
		// TODO Auto-generated constructor stub
	}
	private String brand;
	private String location;
	private int price;
	private double maxSpeed;
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public Car(String brand, String location, int price) {
		super();
		this.brand = brand;
		this.location = location;
		this.price = price;
	}
	/* 如果xml给定的参数无法明显区分为int/double时，后一个析构函数将覆盖前一个 */
	public Car(String brand, String location, double maxSpeed) {
		super();
		this.brand = brand;
		this.location = location;
		this.maxSpeed = maxSpeed;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", location=" + location + ", price=" + price + ", maxSpeed=" + maxSpeed + "]";
	}
	
	

}
