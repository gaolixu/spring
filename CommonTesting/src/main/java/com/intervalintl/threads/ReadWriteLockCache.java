package com.intervalintl.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCache {

	private Map<String, Object> map = new HashMap<String, Object>();// ???
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public static void main(String[] args) {
   Float f = 0.0000099999999999999f;
   Double d= (double) 999;
   System.out.println(f);
   System.out.println(d);
	}

	public Object get(String id) {
		Object value = null;
		rwl.readLock().lock();// ??????,??????
		try {
			value = map.get(id);
			if (value == null) { // ???????????,???
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if (value == null) {
						value = "aaa"; // ???????????,?????????
					}
				} finally {
					rwl.writeLock().unlock(); // ????
				}
				rwl.readLock().lock(); // ??????
			}
		} finally {
			rwl.readLock().unlock(); // ??????
		}
		return value;
	}

}
