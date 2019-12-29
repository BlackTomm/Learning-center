package com.spring.bean;

import java.util.Properties;

public class DataSource {

	public DataSource() {
		// TODO Auto-generated constructor stub
	}
	private Properties properties;
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	public String toString() {
		return "DataSource [properties=" + properties + "]";
	}
	

}
