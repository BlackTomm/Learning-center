package com.spring.bean.cycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext atx= new ClassPathXmlApplicationContext("bean-cycle.xml");
		Car car=(Car) atx.getBean("car");
		System.out.println(car);
		

	}

}
