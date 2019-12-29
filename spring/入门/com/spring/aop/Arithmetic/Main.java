package com.spring.aop.Arithmetic;

public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator target=new ArithmeticCalculatorImpl();
        ArithmeticCalculator proxy= new LoggingProxy(target).getLoggingProxy();

        int result=proxy.add(1,2);
        System.out.println("-->"+result);

        int result1=proxy.mul(3,2);
        System.out.println("-->"+result1);
    }
}
