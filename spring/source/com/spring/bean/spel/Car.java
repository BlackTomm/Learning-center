package com.spring.bean.spel;

public class Car {

	public Car() {
		// TODO Auto-generated constructor stub
	}
	private String brand;
	private int price;
	private double tyrepremiter; //轮胎周长
	
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
	
	public double getTyrepremiter() {
		return tyrepremiter;
	}
	public void setTyrepremiter(double tyrepremiter) {
		this.tyrepremiter = tyrepremiter;
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", tyrepremiter=" + tyrepremiter + "]";
	}
	
	

}
