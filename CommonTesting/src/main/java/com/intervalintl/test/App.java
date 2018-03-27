package com.intervalintl.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	
	@SuppressWarnings("resource") 
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AsyncTest test = (AsyncTest) context.getBean("AsyncTest");
		test.runIt();
		// test.printBalanceAsync("test"); 2. this is also working too,because AsyncTest is proxied by cglib
 
		
		System.out.println("Thread: "+Thread.currentThread().getName() + " comes to the end...");
	}

}
