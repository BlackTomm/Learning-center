package com.spring.bean.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		  ApplicationContext atx=new ClassPathXmlApplicationContext("bean-autowired.xml"); 
		  Person person=(Person) atx.getBean("person1"); 
		  System.out.println(person);
		  
		  Address addressCopy=(Address) atx.getBean("addressCopy");
		  System.out.println(addressCopy);
		
		/*
		 * Address addressCopy=(Address) atx.getBean("addressCopy");
		 * System.out.println(addressCopy);
		 */
	
		ApplicationContext stx=new ClassPathXmlApplicationContext("bean-scope.xml");
		Car carScope1=(Car) stx.getBean("carScope");
		Car carScope2=(Car) stx.getBean("carScope");
		System.out.println(carScope1==carScope2);
		
	}

}
