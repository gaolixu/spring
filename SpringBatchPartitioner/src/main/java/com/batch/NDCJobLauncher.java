package com.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class NDCJobLauncher extends SimpleJobLauncher{
	
	private static final Logger LOGGER = Logger.getLogger(NDCJobLauncher.class.getName());
	static final String MDC_KEY = "batch-job";
	 
	 @Override
	 public JobExecution run(Job job, JobParameters jobParameters)     throws JobExecutionAlreadyRunningException, JobRestartException,        JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	  // MDC.put(MDC_KEY, "MDC---:"+job.getName());
	  // NDC.push("NDC---:" + job.getName());
	   LOGGER.info("Start job...." + job.getName());
	   try {
	     return super.run(job, jobParameters);
	   } finally {
	    // MDC.remove(MDC_KEY);
	   }
	 }
	 
	 public void afterPropertiesSet() throws Exception
		 {
		    //setTaskExecutor(new SimpleAsyncTaskExecutor());
		/* MdcThreadPoolTaskExecutor poolTaskExecutor = new MdcThreadPoolTaskExecutor();  
		 poolTaskExecutor.setQueueCapacity(10000);  
		 poolTaskExecutor.setCorePoolSize(5);  
		 poolTaskExecutor.setMaxPoolSize(10);  
		 poolTaskExecutor.setKeepAliveSeconds(5000);  
		 poolTaskExecutor.initialize(); 
		    super.setTaskExecutor(poolTaskExecutor);*/
		 }
	 
	 /* @Override
	  public void setTaskExecutor(TaskExecutor taskExecutor) {
	     super.setTaskExecutor(
	       new MDCPopulatingTaskExecutorDecorator(taskExecutor));
	  }
	 
	  private static class MDCPopulatingTaskExecutorDecorator               implements TaskExecutor {
	 
	    private TaskExecutor targetExecutor;
	 
	    MDCPopulatingTaskExecutorDecorator(TaskExecutor targetExecutor) {
	      this.targetExecutor = targetExecutor;
	    }
	 
	    @Override
	    public void execute(final Runnable task) {
	      final String mdcValue = (String) MDC.get(MDC_KEY);
	      targetExecutor.execute(new Runnable() {
			@Override
			public void run() {
				MDC.put(MDC_KEY, mdcValue);
				try {
					task.run();
				} finally {
					MDC.remove(MDC_KEY);
				}
				// TODO Auto-generated method stub
				
			}
	      });
	    }
	  }*/

}
