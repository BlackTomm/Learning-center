package com.spring.bean.spel;

public class Address {

	public Address() {
		// TODO Auto-generated constructor stub
	}

	private String city;
	private String street;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + "]";
	}
}
