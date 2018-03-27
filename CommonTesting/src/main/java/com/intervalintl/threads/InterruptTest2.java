package com.intervalintl.threads;

public class InterruptTest2 extends Thread {
	volatile boolean stop = false;

	public static void main(String args[]) throws Exception {
		InterruptTest2 thread = new InterruptTest2();
		System.out.println("Starting thread...");
		thread.start();
		Thread.sleep(3000);
		System.out.println("Asking thread to stop...");

		thread.stop = true;
		Thread.sleep(3000);
		System.out.println("Stopping application...");
		// System.exit( 0 );
	}

	public void run() {
		while (!stop) {
			System.out.println("Thread is running...");
			long time = System.currentTimeMillis();
			while ((System.currentTimeMillis() - time < 1000) && (!stop)) {
			}
		}
		System.out.println("Thread exiting under request...");
	}
}