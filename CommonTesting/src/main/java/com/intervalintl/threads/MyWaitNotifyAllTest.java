package com.intervalintl.threads;

import java.util.ArrayList;
import java.util.List;

public class MyWaitNotifyAllTest {
	public static void main(String[] args) {
		MyWaitNotifyAll myStack = new MyWaitNotifyAll();
		ThreadA threadA = new ThreadA(myStack);
		ThreadA threadA2 = new ThreadA(myStack);
		ThreadA threadA3 = new ThreadA(myStack);
		ThreadB threadB = new ThreadB(myStack);
		ThreadB threadB2 = new ThreadB(myStack);
		ThreadB threadB3 = new ThreadB(myStack);
		threadA.start();
		threadA2.start();
		threadA3.start();
		threadB.start();
		threadB2.start();
		threadB3.start();
	}
}
