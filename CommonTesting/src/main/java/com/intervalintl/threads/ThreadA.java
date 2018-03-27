package com.intervalintl.threads;

public class ThreadA extends Thread {  
	  private MyWaitNotifyAll myWaitNotifyAll;  
	  public ThreadA(MyWaitNotifyAll myWaitNotifyAll) {  
	      this.myWaitNotifyAll = myWaitNotifyAll;  
	  }  
	  @Override  
	  public void run() {  
	   while(true){  
	     myWaitNotifyAll.push();  
	   }  
	 }  
}