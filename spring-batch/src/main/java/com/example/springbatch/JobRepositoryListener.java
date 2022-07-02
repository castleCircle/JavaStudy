//package com.example.springbatch;
//
//import org.springframework.batch.core.BatchStatus;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JobRepositoryListener implements JobExecutionListener {
//
//  @Autowired
//  private JobRepository jobRepository;
//
//
//  @Override
//  public void beforeJob(JobExecution jobExecution) {
//
//  }
//
//  @Override
//  public void afterJob(JobExecution jobExecution) {
//    final String jobName = jobExecution.getJobInstance().getJobName();
//
//    final JobParameters jobParameters = new JobParametersBuilder().addString("requestDate",
//        "20210102").toJobParameters();
//
//    final JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
//    if(lastJobExecution != null){
//      for(StepExecution execution : lastJobExecution.getStepExecutions()){
//        final BatchStatus status =
//            execution.getStatus();
//        final ExitStatus exitStatus =
//            execution.getExitStatus();
//        System.out.println("status = " + status);
//        System.out.println("exitsStatue = " + exitStatus);
//        System.out.println("stepName:" + execution.getStepName());
//      }
//    }
//  }
//}
