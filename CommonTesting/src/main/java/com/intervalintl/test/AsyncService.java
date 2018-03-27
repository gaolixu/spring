package com.intervalintl.test;

import java.util.concurrent.Future;


public interface AsyncService {

	
	
	 Future<Long> callAsync(String account) ;
	
}
