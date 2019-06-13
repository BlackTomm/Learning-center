package com.spring.bean.cycle;

public class Car {

	public Car() {
		// TODO Auto-generated constructor stub
		System.out.println("Car's Constrcuctor...");
	}
	private String brand;
	
	public void setBrand(String brand) {
		this.brand=brand;
		System.out.println("setBrand...");
	}
	
	public void init() {
		System.out.println("initial...");
	}
	public void destroy() {
		System.out.println("destroy...");
	}
	
}
