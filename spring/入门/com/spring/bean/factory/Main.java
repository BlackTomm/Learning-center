package com.spring.bean.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		ApplicationContext atx=new ClassPathXmlApplicationContext("bean-factory.xml");
		Car car1 = (Car) atx.getBean("car1");
		System.out.println(car1);
		
		Car car2 = (Car) atx.getBean("car2");
		System.out.println(car2);

 	}

}
