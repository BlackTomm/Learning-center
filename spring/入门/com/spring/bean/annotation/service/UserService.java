package com.spring.bean.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.bean.annotation.repository.UserRepository;
import com.spring.bean.annotation.repository.UserRepositoryImpl;

@Service
public class UserService {
	
	private UserRepositoryImpl ur;
	
	/*
	 * 当在userRepository中未指定具体bean id名时，须声明Qualifier,
	 * 且须保证id与getbean中一致皆为userRepository接口实现类,即userRepositoryImpl
	 */
	@Autowired
	@Qualifier("userRepositoryImpl")   
	public void setUr(UserRepositoryImpl ur) {
		this.ur=ur;
	}
	
	
	public void add() {
		System.out.println("UserService add...");
		ur.save();
	}
}
