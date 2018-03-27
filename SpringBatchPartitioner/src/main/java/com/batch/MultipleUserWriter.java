package com.batch;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.batch.item.ItemWriter;

public class MultipleUserWriter   implements ItemWriter<User>{
	private static final Logger LOGGER = Logger.getLogger(MultipleUserWriter.class.getName());
	@Override
	public void write(List<? extends User> users) throws Exception {
		for(User user:users){
			MDC.put("batch-job", MDC.get("batch-job")+"--------"+user.getId());
			LOGGER.info("[[[[[[MultipleUserWriter]]]]]]]]: User : " + user.getId());
			//System.out.println(user.getId());
		}
		
	}

}
