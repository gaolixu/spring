package com.intervalintl.threads;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteQueue {
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public void get() {
		rwl.readLock().lock();
		System.out.println(Thread.currentThread().getName()	+ " be ready to read data!");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()	+ "have read data :" + data);
		rwl.readLock().unlock(); // ????,????finnaly??
	}

	public void put(Object data) {

		rwl.writeLock().lock();// ???,?????????????
		System.out.println(Thread.currentThread().getName()	+ " be ready to write data!");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName()	+ " have write data: " + data);

		rwl.writeLock().unlock();// ????
	}
}
