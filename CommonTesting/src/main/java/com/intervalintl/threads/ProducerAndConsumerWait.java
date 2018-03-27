package com.intervalintl.threads;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ProducerAndConsumerWait {
	private int maxSize;
	private List<Date> storage;

	ProducerAndConsumerWait(int size) {
		maxSize = size;
		storage = new LinkedList<>();
	}

	// 生产方法
	public synchronized void put() {
		try {
			while (storage.size() == maxSize) {// 如果队列满了
				System.out
						.print(Thread.currentThread().getName() + ": wait \n");
				;
				wait();// 阻塞线程
			}
			storage.add(new Date());
			System.out.print(Thread.currentThread().getName() + ": put:"
					+ storage.size() + "\n");
			Thread.sleep(1000);
			notifyAll();// 唤起线程
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 消费方法
	public synchronized void take() {
		try {
			while (storage.size() == 0) {// 如果队列满了
				System.out
						.print(Thread.currentThread().getName() + ": wait \n");
				;
				wait();// 阻塞线程
			}
			Date d = ((LinkedList<Date>) storage).poll();
			System.out.print(Thread.currentThread().getName() + ": take:"
					+ storage.size() + "\n");
			Thread.sleep(1000);
			notifyAll();// 唤起线程
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		ProducerAndConsumerWait buffer = new ProducerAndConsumerWait(10);
		Producer2 producer = new Producer2(buffer);
		Consumer2 consumer = new Consumer2(buffer);
		// 创建线程执行生产和消费
		for (int i = 0; i < 3; i++) {
			new Thread(producer, "producer-" + i).start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread(consumer, "consumer-" + i).start();
		}
	}
}

// 生产者
class Producer2 implements Runnable {
	private ProducerAndConsumerWait buffer;

	Producer2(ProducerAndConsumerWait b) {
		buffer = b;
	}

	@Override
	public void run() {
		while (true) {
			buffer.put();
		}
	}
}

// 消费者
class Consumer2 implements Runnable {
	private ProducerAndConsumerWait buffer;

	Consumer2(ProducerAndConsumerWait b) {
		buffer = b;
	}

	@Override
	public void run() {
		while (true) {
			buffer.take();
		}
	}
}