package com.spring.bean.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository//("userRepository") //注意bean id小写
public class UserRepositoryImpl implements UserRepository {
	
	@Override
	public void save() {
		System.out.println("UserRepositoryImpl save...");
	}

}
