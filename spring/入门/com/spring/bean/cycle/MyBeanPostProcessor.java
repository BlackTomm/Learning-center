package com.spring.bean.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
	
	//bean后处理器会依次实现所有bean,一般需添加过滤
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
		System.out.println("postProcessorBeforeInitialization: "+bean+" beanName: "+beanName);
		return null;
	}     
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("Called postProcessAfterInitialization() for :" + beanName);
        return bean;
    }
	public MyBeanPostProcessor() {
		// TODO Auto-generated constructor stub
	}

}
