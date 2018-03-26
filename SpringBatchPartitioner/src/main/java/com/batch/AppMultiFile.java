package com.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMultiFile {

	public static void main(String[] args) {

		AppMultiFile obj = new AppMultiFile();
		obj.run();

	}

	private void run() {

		String[] springConfig = { "spring/batch/jobs/job-multiReader.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("multiReaderJob");

		try {

			//JobParameters param = new JobParametersBuilder().addString("age", "20").toJobParameters();

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}

}
