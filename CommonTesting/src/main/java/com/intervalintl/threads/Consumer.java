package com.intervalintl.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {  
    private final BlockingQueue queue;  
  
    Consumer(BlockingQueue q) {  
        queue = q;  
    }  
  
    public void run() {  
        try {  
            while (true) {  
                consume(queue.take());// 从缓冲队列取出产品  
            }  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
  
    void consume(Object x) {  
        System.out.println(Thread.currentThread().getName() + " 消费："+x);// 消费产品  
    }  
    public static void main(String[] args) {  
    	BlockingQueue q = new ArrayBlockingQueue<Integer>(10);// 或其他实现  
    	Producer p = new Producer(q);  
    	Consumer c1 = new Consumer(q);  
    	Consumer c2 = new Consumer(q);  
    	new Thread(p).start();  
    	new Thread(c1).start();  
    	new Thread(c2).start();  
    }  
}  
  