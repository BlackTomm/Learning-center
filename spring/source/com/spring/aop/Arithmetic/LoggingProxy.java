package com.spring.aop.Arithmetic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LoggingProxy {

    //创建代理对象
    private ArithmeticCalculator target;

    public LoggingProxy(ArithmeticCalculator target){
        this.target=target;
    }

    public ArithmeticCalculator getLoggingProxy(){
        ArithmeticCalculator proxy=null;

        //代理对象类加载器
        ClassLoader loader=target.getClass().getClassLoader();
        //获取代理对象中的方法
        Class[] interfaces = new Class[] {ArithmeticCalculator.class};
        //当调用代理对象时,将执行以下代码
        InvocationHandler h=new InvocationHandler() {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            /*
             * proxy:正在返回的那个代理对象，一般情况下，在invoke方法中都不使用该对象
             * method:正在被调用的方法
             * args:调用方法时传入的参数
             */
            String methodName =method.getName();
            System.out.println("The method "+methodName+" begins with "+ Arrays.asList(args));
            Object result=method.invoke(target,args);
            System.out.println("The method "+ methodName+" ends with "+result);
            return result;

        	}
        };

        proxy= (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces,h);
        return  proxy;

    }
}