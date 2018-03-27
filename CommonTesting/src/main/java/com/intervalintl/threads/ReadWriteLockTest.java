package com.intervalintl.threads;

import java.util.Random;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final ReadWriteQueue q3 = new ReadWriteQueue();
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						q3.get();
					}
				}

			}.start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						q3.put(new Random().nextInt(10000));
					}
				}

			}.start();
		}

	}
}
