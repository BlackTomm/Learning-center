package com.spring.bean.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.annotation.controller.UserController;
import com.spring.bean.annotation.repository.UserRepository;
import com.spring.bean.annotation.service.UserService;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext atx=new ClassPathXmlApplicationContext("bean-annotation.xml");
		
		TestObject to = (TestObject) atx.getBean("testObject");
		System.out.println(to);
		  
		UserController userController = (UserController) atx.getBean("userController"); 
		System.out.println(userController);
		userController.excute();
		  
		UserService userService = (UserService) atx.getBean("userService");
		System.out.println(userService);
		 
		
		UserRepository userRepository = (UserRepository) atx.getBean("userRepositoryImpl");
		System.out.println(userRepository);
		
		((AbstractApplicationContext) atx).close();
	}
}
