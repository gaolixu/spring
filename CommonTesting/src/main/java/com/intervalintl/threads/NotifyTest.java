package com.intervalintl.threads;

public class NotifyTest {

	public synchronized void testWait() {
		System.out.println(Thread.currentThread().getName() + " Start-----");
		try {
			Thread.sleep(2000L);
			wait(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " End-------");
	}

	public static void main(String[] args) throws InterruptedException {
		final NotifyTest test = new NotifyTest();
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					test.testWait();
				}
			}).start();
		}

		synchronized (test) {
			System.out.println("Notify one");
			test.notify();
		}
		Thread.sleep(3000);
		System.out.println("-----------???-------------");

		synchronized (test) {
			System.out.println("Notify All");
			test.notifyAll();
		}
		
		
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (test) {
			System.out.println("Notify All");
			test.notifyAll();
		}
		
	}
}
