package com.intervalintl.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

 
public class AsyncTest {
	
	@Autowired
	protected AsyncService asyncService;
	

	public void runIt() {
		//AsyncTest t = new AsyncTest();
		// printBalanceAsync("Ben"); //not working, aop won't be invoked inside
		/*try {
			System.out.println("bal=" + bal.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println("Thread: "+Thread.currentThread().getName() + " will invoke async service soon...");
		asyncService.callAsync("test");
		/*Future<Long> bal = asyncService.callAsync("test"); cannot use future.get here, or the thread will wait to get the result
		 * 
		try {
			System.out.println("Async result is :"+bal.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println("Thread: "+Thread.currentThread().getName() + " async service has been invoked and still running...");

	}

	@Async
	public Future<Long> printBalanceAsync(String account) {
		Long balance = 0L;
		for (Long i = 1L; i < 100000L; i++) {
			balance = i;
			System.out.println("Thread: "+Thread.currentThread().getName() +" is ruuning " + balance);
		}
		return new AsyncResult<Long>(balance);
	}

	
}
