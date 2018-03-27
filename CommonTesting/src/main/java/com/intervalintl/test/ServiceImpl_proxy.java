package com.intervalintl.test;

public class ServiceImpl_proxy implements Service {
	
	private Service service  = new ServiceImpl();
	
	 public void say() {
		 System.out.println("service_proxy  before saying");
		 service.say();
		 System.out.println("service_proxy  after saying");
	}
	
	
	 public void walk() {
		 System.out.println("service_proxy  before walking");
		 service.walk();
		 System.out.println("service_proxy  after walking");
	}
}
