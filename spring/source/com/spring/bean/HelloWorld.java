package com.spring.bean;

public class HelloWorld {
	private String name;

	
	public HelloWorld() { // TODO Auto-generated constructor stub
		System.out.println("HelloWorld 析构函数"); 
	}
	 
	public HelloWorld(String name) {
		// TODO Auto-generated constructor stub
		System.out.println("HelloWorld析构: "+name);
	}
	
	
	public void setName(String name) {
		this.name=name;
	}
	public void hello() {
		System.out.println("hello: "+name);
	}

}
