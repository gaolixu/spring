package com.intervalintl.test;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

	
	@Async
	public Future<Long> callAsync(String account) {
		Long balance = 0L;
		for (Long i = 1L; i < 100000L; i++) {
			balance = i;
			System.out.println("Async Thread: "+Thread.currentThread().getName() +" is still running "+ balance);
		}
		return new AsyncResult<Long>(balance);
	}
	
}
