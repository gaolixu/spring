package com.intervalintl.threads;

public class ThreadB extends Thread {
	private MyWaitNotifyAll myWaitNotifyAll;

	public ThreadB(MyWaitNotifyAll myWaitNotifyAll) {
		this.myWaitNotifyAll = myWaitNotifyAll;
	}

	@Override
	public void run() {
		while (true) {
			myWaitNotifyAll.pop();
		}
	}
}