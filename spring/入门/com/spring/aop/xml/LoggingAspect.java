package com.spring.aop.xml;

import java.util.List;
import java.util.Arrays;

import javax.sound.sampled.LineListener;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


public class LoggingAspect {
	
	 public void beforeMethod(JoinPoint joinPoint){
	     
	     String methodName = joinPoint.getSignature().getName();
	     List<Object> args=Arrays.asList(joinPoint.getArgs());
	     System.out.println("The method "+methodName+" begins with "+args);
	 }
	
	 public void afterMethod(JoinPoint joinPoint) {
		 String methodName = joinPoint.getSignature().getName();
		 System.out.println("The method "+methodName+" ends.");
	 }
	
	 public void afterReturnning(JoinPoint joinPoint, Object result) {
		 String methodName =joinPoint.getSignature().getName();
		 System.out.println("This method "+methodName+ " returns with "+result);
	 }


	 public void afterThrowing(JoinPoint joinPoint, NullPointerException e) {
		 String methodName = joinPoint.getSignature().getName();
		 System.out.println("The method "+methodName+" is throwing Exception:"+e);
	 }
	 
	 
	 public Object around(ProceedingJoinPoint pjp) {
		 
		 Object result = null;
		 String methodName = pjp.getSignature().getName();
		 
		 try {
			 //前置通知
			 System.out.println("环绕前置通知—>方法"+methodName+" 参数包含："+Arrays.asList(pjp.getArgs()));
			 //执行目标方法
			 result =pjp.proceed();
			 //后置通知
			 System.out.println("环绕后置通知—>方法"+methodName+" 结束.");
			 
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println("环绕异常—>方法"+methodName+" ，异常为："+e);
			throw new RuntimeException(e);
		}
		 
		 return result;
	 }
	 
}
