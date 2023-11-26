package com.example.springbatchtest;

import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchTestApplication.class, args);
  }

}
