package com.dan.learning.springbatch.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String args[]) {
		
		final Logger logger = LoggerFactory.getLogger(App.class);
		
		String[] springConfig = { "spring/batch/config/database.xml",
								  "spring/batch/config/context.xml",
								  "spring/batch/jobs/job-report.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher)context.getBean("jobLauncher");
		Job job = (Job)context.getBean("reportJob");
		
		logger.info("Starting Job: reportJob");
		
		try {
			
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			logger.info("Exit Status : " + execution.getStatus());
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		logger.info("Done with Report Job!!!");
		
		((ConfigurableApplicationContext)context).close();
		
	}
	
}
