package com.spring.aop.xml;

import java.util.Arrays;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class ValidationAspect {
	@Before("LoggingAspect.declareJointPointExpression()")
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("validate: "+Arrays.asList(joinPoint.getArgs()));
	}

}
