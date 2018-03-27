package com.intervalintl.test;

public class Child extends Parent {

	
	
	public void say(){
		System.out.println("child saying !!!");
	}
	
	
	
	public static void main(String[] args) {
		Parent p = new Child();
		p.walk();
	}
}
