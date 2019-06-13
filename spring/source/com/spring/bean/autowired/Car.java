package com.spring.bean.autowired;

public class Car {

	public Car() {
		// TODO Auto-generated constructor stub
	}
	private String brand;
	private String location;
	private int price;
	private double maxSpeed;
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


	@Override
	public String toString() {
		return "Car [brand=" + brand + ", location=" + location + ", price=" + price + ", maxSpeed=" + maxSpeed + "]";
	}
	

}
