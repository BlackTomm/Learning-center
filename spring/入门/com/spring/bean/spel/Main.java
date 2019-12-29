package com.spring.bean.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {

	public static void main(String[] args) {
		
		ApplicationContext atx=new ClassPathXmlApplicationContext("bean-spel.xml");
		Address address=(Address) atx.getBean("address");
		System.out.println(address);
		
		Car car=(Car) atx.getBean("car");
		System.out.println(car);
		
		Person person = (Person) atx.getBean("person");
		System.out.println(person);
	}
}

