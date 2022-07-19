package com.example.springbatch;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;


  @Bean
  public Job batchJob() {
    return jobBuilderFactory.get("parentJob")
        .start(jobStep(null))
        .next(step2())
        .build();
  }

  @Bean
  public Step jobStep(JobLauncher jobLauncher){
    return this.stepBuilderFactory.get("jobStep")
        .job(childJob())
        .launcher(jobLauncher)
        .parametersExtractor(jobParametersExtractor())
        .listener(new StepExecutionListener() {
          @Override
          public void beforeStep(StepExecution stepExecution) {
            stepExecution.getExecutionContext().putString("name","user1");
          }

          @Override
          public ExitStatus afterStep(StepExecution stepExecution) {
            return null;
          }
        })
        .build();
  }

  private DefaultJobParametersExtractor jobParametersExtractor(){
    DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
    extractor.setKeys(new String[]{"name"});
    return extractor;
  }


  @Bean
  public Job childJob(){
    return this.jobBuilderFactory.get("childJob")
        .start(step1())
        .build();
  }

  @Bean
  public Step step1(){
    return stepBuilderFactory.get("step1")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
              throws Exception {
            System.out.println("step1 was executed");
            throw new RuntimeException("step1 was faild");
//            return RepeatStatus.FINISHED;
          }
        })
        .allowStartIfComplete(true)
        .build();
  }

  @Bean
  public Step step2(){
    return stepBuilderFactory.get("step2")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
              throws Exception {
            System.out.println("step2 was executed");
            return RepeatStatus.FINISHED;
          }
        })
        .startLimit(3)
        .build();
  }


}
