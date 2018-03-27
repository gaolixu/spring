package com.intervalintl.test;

public class ServiceImpl implements Service {
	 public void say() {
		 System.out.println("service is saying");
	}
	
	
	 public void walk() {
		 System.out.println("service is walking");
		 say();
	}
}
