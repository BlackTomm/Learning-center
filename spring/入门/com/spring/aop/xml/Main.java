package com.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main {
		    public static void main(String[] args) {

		        //创建spring IOC容器
		        ApplicationContext atx= new ClassPathXmlApplicationContext("aop-xml.xml");
		        //从IOC中获取bean实例
		        ArithmeticCalculator arithmeticCalculator=atx.getBean(ArithmeticCalculator.class);
		        //使用bean
		        int result=arithmeticCalculator.add(3,7);
		        System.out.println("Result: "+result);
		        
		        int result1=arithmeticCalculator.div(17,12);
		        System.out.println("Result: "+result1);
		        
		    }

}
