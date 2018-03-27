package com.intervalintl.test;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadExitTest {
	public static void main3(String[] args) {

		System.out.println("Main thread started");
		/*new Thread(new Runnable() {

			public void run() {
				System.out.println("Second thread started");
				try {
					for (int i = 0; i <= 100; i++) {
						System.out.println("sleeeping....");
						Thread.sleep(2000); // wait two seconds
					}
				} catch (Exception e) {
				}
				System.out.println("Second thread (almost) finished");
			}
		}).start();*/
		
		Runnable r = new Runnable(){
			
			public void run(){  
			System.out.println(Thread.currentThread().getName() + "开始");  
			for (int i = 0; i <= 100; i++) {
				System.out.println("sleeeping....");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // wait two seconds
			}
			System.out.println(Thread.currentThread().getName() + "结束.");  
			}  
			} ; 
		Thread t = new Thread(r); 
		//t.setDaemon(true);
		t.start();
		//Thread.currentThread().setDaemon(true);
		
		
		try {
			Thread.sleep(2000); // wait two seconds
		} catch (Exception e) {
		}
		System.out.println("Main thread (almost) finished");
		//System.exit(0);
	}

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("Timer thread is running...");
				try {
					Thread.sleep(8000); // wait two seconds
				} catch (Exception e) {
				}
			}

		}, 500, 2000);

		System.out.println("Main thread ends!");
		try {
			Thread.sleep(2000); // wait two seconds
		} catch (Exception e) {
		}
		//System.exit(0);
	}
}
