package com.batch;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.batch.item.ItemWriter;

public class UserWriter   implements ItemWriter<User>{
	private static final Logger LOGGER = Logger.getLogger(UserWriter.class.getName());
	@Override
	public void write(List<? extends User> users) throws Exception {
		for(User user:users){
			MDC.put("batch-job", MDC.get("batch-job")+"--------"+user.getId());
			LOGGER.info("Log4j: User : " + user.getId());
			//System.out.println(user.getId());
		}
		
	}

}
