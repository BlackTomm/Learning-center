package com.spring.bean.factory;

import java.util.HashMap;
import java.util.Map;


public class StaticCarFactory {
//	静态工厂方法
	private static Map<String, Car> cars=new HashMap<String, Car>();
	
	static {
		cars.put("BMW", new Car("BMW","上海",3000));
		cars.put("BBO", new Car("BBO","天津",1000));
	}
	
	public static Car getCar(String name) {
		return cars.get(name);
	}

}
