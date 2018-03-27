package com.intervalintl.threads;

import java.util.ArrayList;
import java.util.List;

public class MyWaitNotifyAll {
	private List<String> list = new ArrayList<String>();  
    synchronized public void push() {  
        while (list.size() == 1) {  
            try {  
                this.wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        list.add("product one");  
        this.notifyAll();  
        System.out.println("生产了:" + list.size() + "个面包");  
    }  
    synchronized public void pop() {  
        while (list.size() == 0) {  
            try {  
                System.out.println("没有面包可消费了:" + Thread.currentThread().getName() + "线程呈wait状态");  
                this.wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        list.remove(0);  
        this.notifyAll();  
        //notify不是立即释放线程,得等该方法执行完后才释放锁  
        System.out.println("消费完还剩:" + list.size() + "个面包");  
    }  
}
