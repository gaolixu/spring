package com.intervalintl.threads;

public class TraditionalThreadCommunication {
	/**
	 * 5 * 经验：涉及到线程互斥共享，应该想到将同步方法写在资源里面，而不是写在线程代码块中 在资源中判断标记的时候，最好用while语句 6
	 */
	public static void main(String[] args) {
		final Output output = new Output();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 50; i++) {
					output.sub(10);
				}
			}
		}).start();
		for (int i = 0; i < 50; i++) {
			output.main(i);
		}
	}
}

class Output {
	private boolean flag = true;

	public synchronized void sub(int i) {
		while (!flag) {// 用while比用if更加健壮，原因是即使线程被唤醒了，也判断一下是不是真的该它执行了
						// 防止伪唤醒的事件发生。
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		for (int j = 0; j < 10; j++) {
			System.out.println(i + "子线程运行" + j);
		}
		flag = false;// 记得要改变一下标记的状态
		this.notify();// 最后要唤醒其他要使用该锁的线程
	}

	public synchronized void main(int i) {
		while (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		for (int j = 0; j < 100; j++) {
			System.out.println(i + "主线程运行" + j);
		}
		flag = true;
		this.notify();
	}
}