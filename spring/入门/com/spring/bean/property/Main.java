package com.spring.bean.property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import javax.sql.DataSource;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		ApplicationContext atx=new ClassPathXmlApplicationContext("bean-property.xml");
		
		DataSource dataSource=(DataSource) atx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}
}
