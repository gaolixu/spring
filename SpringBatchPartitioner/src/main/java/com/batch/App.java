package com.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		App obj = new App();
		obj.run();

	}

	private void run() {

		String[] springConfig = { "spring/batch/jobs/job-partitioner.xml", "spring/batch/jobs/job-multiReader.xml"};

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("partitionJob");
		
		Job multiReaderjob = (Job) context.getBean("multiReaderJob");

		try {

			//JobParameters param = new JobParametersBuilder().addString("age", "20").toJobParameters();

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());
			
			JobExecution multiReaderExecution = jobLauncher.run(multiReaderjob, new JobParameters());
			System.out.println("Exit Status : " + multiReaderExecution.getStatus());
			System.out.println("Exit Status : " + multiReaderExecution.getAllFailureExceptions());
			
			JobParameters param = new JobParametersBuilder().addString("PartitionerRunTime", new Date().toString()).toJobParameters();
			JobExecution execution2 = jobLauncher.run(job, param);
			System.out.println("Exit Status : " + execution2.getStatus());
			System.out.println("Exit Status : " + execution2.getAllFailureExceptions());
			
			JobParameters param2 = new JobParametersBuilder().addString("MultiReaderRunTime", new Date().toString()).toJobParameters();
			JobExecution multiReaderExecution2 = jobLauncher.run(multiReaderjob, param2);
			System.out.println("Exit Status : " + multiReaderExecution2.getStatus());
			System.out.println("Exit Status : " + multiReaderExecution2.getAllFailureExceptions());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}

}
