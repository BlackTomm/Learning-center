package com.spring.aop.impl;

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


/**
 * 定义切面前必须先使用@component声明为bean
 * 然后@Aspect声明切面
 * 使用@Order定义切面的优先级，值越小优先级越高
 */
@Order(2)
@Component
@Aspect
public class LoggingAspect {
	
	/**
	 * 声明切入点表达式，以该切入点为始调用的方法不必使用类似
	 * @Before("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(int ,int))")
	 * 而是@Before("declareJointPointExpression()")，方法名不同，填入则会有差异
	 * 对于统一文件夹下方法，若使用同一切点可使用切点定义处  文件名.declareJointPointExpression()
	 */
	@Pointcut("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(int ,int))")
	public void declareJointPointExpression() {}
	
	/**
	 * 在执行ArithmeticCalculator实现类中对应方法之前执行
	 * 注意不要list包需引用java.util.List,而非awt
	 */
	 @Before("declareJointPointExpression()")
	 public void beforeMethod(JoinPoint joinPoint){
	     
	     String methodName = joinPoint.getSignature().getName();
	     List<Object> args=Arrays.asList(joinPoint.getArgs());
	     System.out.println("The method "+methodName+" begins with "+args);
	 }
	 
	 /**
	  * 在方法执行之后执行，无论是否有异常
	  */
	 @After("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(int ,int))")
	 public void afterMethod(JoinPoint joinPoint) {
		 String methodName = joinPoint.getSignature().getName();
		 System.out.println("The method "+methodName+" ends.");
	 }
	 
	 /**
	  * 方法正常结束后执行，可接收返回值
	  * 出现异常后将不会调用该方法
	  */
	 @AfterReturning(value="execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))",returning="result")
	 public void afterReturnning(JoinPoint joinPoint, Object result) {
		 String methodName =joinPoint.getSignature().getName();
		 System.out.println("This method "+methodName+ " returns with "+result);
	 }

	 /**
	  * 出现异常时运行的代码，可捕获特定异常；
	  */
	 @AfterThrowing(value="execution(public void com.spring.aop.impl.ArithmeticCalculator.*(..))",
			 throwing="e")
	 public void afterThrowing(JoinPoint joinPoint, NullPointerException e) {
		 String methodName = joinPoint.getSignature().getName();
		 System.out.println("The method "+methodName+" is throwing Exception:"+e);
	 }
	 
	 /**
	  * 环绕通知，可代替以上四种
	  * 必须携带ProceedingJointPoint 类型参数
	  * 声明环绕后若和前四种发生交叠，将优先执行
	  */
//	 @Around("execution(public int com.spring.aop.impl.ArithmeticCalculator.*(..))")
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
	 
	 
	 /**
	  * 关于JoinPoint与ProceedingJoinPoint间源代码
	  * 
	  * public interface JoinPoint {  
		   String toString();         //连接点所在位置的相关信息  
		   String toShortString();     //连接点所在位置的简短相关信息  
		   String toLongString();     //连接点所在位置的全部相关信息  
		   Object getThis();         //返回AOP代理对象  
		   Object getTarget();       //返回目标对象  
		   Object[] getArgs();       //返回被通知方法参数列表  
		   Signature getSignature();  //返回当前连接点签名  
		   SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置  
		   String getKind();        //连接点类型  
		   StaticPart getStaticPart(); //返回连接点静态部分  
		  }  
		 
		 public interface ProceedingJoinPoint extends JoinPoint {  
		       public Object proceed() throws Throwable;  
		       public Object proceed(Object[] args) throws Throwable;  
		 }  详细参数说明
	  * 
	  */
}
