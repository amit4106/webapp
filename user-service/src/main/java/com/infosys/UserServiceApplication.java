package com.infosys;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication  {

//	@Autowired
//	private Job job;
//
//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Override
//	public void run(String... args) throws Exception {
//		JobParameters parms = new JobParametersBuilder().addLong("StartAt", System.currentTimeMillis())
//				.toJobParameters();
//		jobLauncher.run(job, parms);
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		System.out.println("Spring batch..");
	}

}
