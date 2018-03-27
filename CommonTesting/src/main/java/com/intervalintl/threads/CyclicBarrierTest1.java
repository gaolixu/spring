package com.intervalintl.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest1 {
	public static void main(String [] args) throws InterruptedException {  
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(10, new Runnable() {  
           // @Override  
            public void run() {  
                System.out.println("10 个人都到达会议室，开会");  
            }  
        });  
          
        for(int i=0;i<10;i++){  
            final long tmp=i;  
            Thread thread=new Thread(new Runnable() {  
               // @Override  
                public void run() {  
                    try {  
                        Thread.sleep(1000*(11-tmp));  
                        System.out.println("person"+tmp+" come here");  
                        try {  
                            cyclicBarrier.await();//等待其他线程到达  
                        } catch (BrokenBarrierException e) {  
                            e.printStackTrace();  
                        }  
                    } catch (InterruptedException e) {}  
                }  
            });  
            thread.start();  
        }  
    }  
}
