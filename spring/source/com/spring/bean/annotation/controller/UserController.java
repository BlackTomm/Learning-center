package com.spring.bean.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.spring.bean.annotation.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	public void excute() {
		System.out.println("UserController excute...");
		us.add();
	}
}
