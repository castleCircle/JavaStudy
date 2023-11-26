//package com.example.springbatchtest;
//
//
//import java.util.Date;
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParametersValidator;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.job.CompositeJobParametersValidator;
//import org.springframework.batch.core.job.DefaultJobParametersValidator;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.scope.context.StepContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class HelloWorldJob {
//
//  private final JobBuilderFactory jobBuilderFactory;
//
//  private final StepBuilderFactory stepBuilderFactory;
//
//  private int number = 1;
//
//
//  @Bean
//  public Job job(){
//    return this.jobBuilderFactory.get("basicJob")
//        .start(step1())
//        .incrementer(new DailyJobTimeStamper())
//        .build();
//  }
//
//  @Bean
//  public Step step1(){
//    return this.stepBuilderFactory.get("step1")
//        .tasklet(helloWorldTasklet()).build();
//  }
//  @Bean
//  public Tasklet helloWorldTasklet(){
//    return (contribution, chunkContext) -> {
//
//      System.out.println(number);
//
//      if(number != 3){
//        number++;
//        return RepeatStatus.CONTINUABLE;
//      }else{
//        return RepeatStatus.FINISHED;
//      }
//    };
//  }
//
//
//
//}
