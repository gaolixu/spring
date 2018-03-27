package com.batch;


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

//@Component("appJobExecutionListener")
public class AppJobExecutionListener implements JobExecutionListener {
	
	private final static Logger LOGGER = Logger.getLogger(AppJobExecutionListener.class);
	int i=0;
	static final String MDC_KEY = "batch-job";
	public void beforeJob(JobExecution jobExecution) {
		i++;
		String jobName = jobExecution.getJobInstance().getJobName();
		Long jobId = jobExecution.getJobId();
		
		
		String jonInfo = " JobName:" + jobName + ",JobId" + jobId.toString() + ",Parameters: "+jobExecution.getJobParameters().toString();
		
		
		MDC.put(MDC_KEY, "MDC---:"+i+i+i+i+i+" "+jonInfo);
		//NDC.push("NDC---:"+i+" "+ jobName);
		LOGGER.info("Job Listener : --------------------- Start " + Thread.currentThread().getName() );
	}
		
	public void afterJob(JobExecution jobExecution) {
		    MDC.remove(MDC_KEY);
		   // NDC.pop();
		    LOGGER.info("Job Listener : --------------------- End " );
		    
	}

	
	
}
