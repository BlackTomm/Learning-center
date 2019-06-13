package com.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework。.context.ClassPathXmlApplicationContext;
public class Main {

	
//	  public Main() { // TODO Auto-generated constructor stub 
//		  System.out.println("主析构函数？？？");
//	  }
	 

	  public static void main(String[] args) {
		
//		 HelloWorld helloWorld1= new HelloWorld();
//		 helloWorld1.setName2("MakinTash"); 
//		 helloWorld1.hello();
//		String HelloWorld = "classpath:applicationContext.xml";
		
		//1.创建spring的IOC容器ApplicationContext,而ClassPathXmlApplicationContext指定类加载路径
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.从IOC中获取bean实例
		HelloWorld helloWorld = (HelloWorld) atx.getBean("helloWorld");
		// 3.调用·hello
		helloWorld.hello();
		//((ApplicationContext) ctx).close();
		
		//输出拥有的车
		Car car=(Car) atx.getBean("car");
		System.out.println(car);
		
		Car car2=(Car) atx.getBean("car2");
		System.out.println(car2);
		
		Person person=(Person) atx.getBean("person");
		System.out.println(person);
		
		Person person2=(Person) atx.getBean("person2");
		System.out.println(person2);
		
		Person personList=(Person) atx.getBean("personList");
		System.out.println(personList);
		
		NewPerson personMap=(NewPerson) atx.getBean("personMap");
		System.out.println(personMap);
		
		DataSource dataSource=(DataSource) atx.getBean("dataSource");
		System.out.println(dataSource);
		
		Person personUtil=(Person) atx.getBean("personUtil");
		System.out.println(personUtil);
		
		Person personPspace=(Person) atx.getBean("personPspace");
		System.out.println(personPspace);
	}

}
