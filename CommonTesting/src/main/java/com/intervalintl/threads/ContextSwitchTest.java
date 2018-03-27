package com.intervalintl.threads;

public class ContextSwitchTest {
	private static final long count = 10000;

	public static void main(String[] args) throws Exception {
		concurrency();
		serial();
	}

	private static void concurrency() throws Exception {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int a = 0;
				for (int i = 0; i < count; i++) {
					a += 5;
				}
			}
		});
		thread.start();
		int b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		thread.join();
		long time = System.currentTimeMillis() - start;
		System.out.println("Concurrency:" + time + "ms, b = " + b);
	}

	private static void serial() {
		long start = System.currentTimeMillis();
		int a = 0;
		for (long i = 0; i < count; i++) {
			a += 5;
		}
		int b = 0;
		for (int i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("Serial:" + time + "ms, b = " + b + ", a = " + a);
	}
}
