package com.spring.bean.factory;

import java.util.HashMap;
import java.util.Map;

public class InstanceCarFactory {

	private Map<String, Car> cars=null;
	
	public InstanceCarFactory() {
		cars=new HashMap<String, Car>();
		cars.put("BMW", new Car("BMW","上海",3000));
		cars.put("BBO", new Car("BBO","天津",1000));
	}

	public Car getCar(String brand) {
		return cars.get(brand);
	}
}
