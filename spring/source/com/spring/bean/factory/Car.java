package com.spring.bean.factory;

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
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Car(String brand, String location, int price) {
		super();
		this.brand = brand;
		this.location = location;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", location=" + location + ", price=" + price + ", maxSpeed=" + maxSpeed + "]";
	}
	
}
