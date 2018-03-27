package com.intervalintl.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShareDataDemo {
	 //所有线程共享的数据是datas，但是datas中的每个元素key是Thread，每个元素针对每个线程来说是独立的，value代表不同线程处理的数据
	   private static Map<Thread,Integer> datas = new HashMap<Thread,Integer>();
	     public static void main(String[] args) {
	         for(int i=0;i<2;i++){
	             new Thread(new Runnable() {
	                 
	                 public void run() {
	                     int nextInt = new Random().nextInt();
	                     datas.put(Thread.currentThread(), nextInt);
	                     ///A模块与B模块是独立的，但是A与B共享当前当前线程中的数据
	                     new ModuleA().getThreadData();
	                     new ModuleB().getThreadData();
	                 }
	             }).start();
	         }
	         /*
	30              打印的结果为
	31              Thread-1的ModuleA获取的变量为：-918049793
	32             Thread-0的ModuleA获取的变量为：-1424853148
	33             Thread-0的ModuleB获取的变量为：-1424853148
	34             Thread-1的ModuleB获取的变量为：-918049793
	35  
	36          */
	     }
	     static class ModuleA{
	         public void getThreadData(){
	             System.out.println(Thread.currentThread().getName()+"的ModuleA获取的变量为："+datas.get(Thread.currentThread()));
	         }
	     }
	     static class ModuleB{
	         public void getThreadData(){
	            System.out.println(Thread.currentThread().getName()+"的ModuleB获取的变量为："+datas.get(Thread.currentThread()));
	         }
	     }
}