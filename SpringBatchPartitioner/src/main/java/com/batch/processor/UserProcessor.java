package com.batch.processor;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.batch.User;
import com.batch.UserRowMapper;

@Component("itemProcessor")
@Scope(value = "step")
public class UserProcessor implements ItemProcessor<User, User> {
	
	private static final Logger LOGGER = Logger.getLogger(UserProcessor.class.getName());

	@Value("#{stepExecutionContext[name]}")
	private String threadName;

	@Override
	public User process(User item) throws Exception {
		MDC.put("batch-job", MDC.get("batch-job")+"--------"+item.getId());
		//System.out.println(threadName + " processing : " + item.getId() + " : " + item.getUsername());
		LOGGER.info(threadName + " processing : " + item.getId() + " : " + item.getUsername());

		return item;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
