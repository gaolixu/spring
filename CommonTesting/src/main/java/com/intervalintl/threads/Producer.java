package com.intervalintl.threads;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {  
    private final BlockingQueue queue;  
    private int i;  
  
    Producer(BlockingQueue q) {  
        queue = q;  
    }  
  
    public void run() {  
        try {  
            while (true) { 
            	int p = produce();
                queue.put(p);// 将产品放入缓冲队列  
                System.out.println("Produce："+p);
            }  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    };
    
    int produce() {  
        return i++;// 生产产品  
    }  
}  
  
