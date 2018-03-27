package com.intervalintl.test;

public class ServiceTest {

	public static void main(String[] args) {
		Service service= new ServiceImpl_proxy();
		service.walk();
	}
}
