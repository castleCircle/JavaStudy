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
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
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
    return jobBuilderFactory.get("batchJob")
        .start(step1())
        .next(step2())
        .next(step3())
        .incrementer(new RunIdIncrementer())
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
            return RepeatStatus.FINISHED;
          }
        })
        .build();
  }

  @Bean
  public Step step2(){
    return stepBuilderFactory.get("step2")
        .<String,String>chunk(3)
        .reader(new ItemReader<String>() {
          @Override
          public String read()
              throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
            return null;
          }
        })
        .processor(new ItemProcessor<String, String>() {
          @Override
          public String process(String item) throws Exception {
            return null;
          }
        })
        .writer(new ItemWriter<String>() {
          @Override
          public void write(List<? extends String> items) throws Exception {

          }
        })
        .build();
  }

  @Bean
  public Step step3(){
    return stepBuilderFactory.get("step3")
        .partitioner(step1())
        .gridSize(2)
        .build();
  }

  @Bean
  public Step step4(){
    return stepBuilderFactory.get("step4")
        .job(job())
        .build();
  }

  @Bean
  public Step step5(){
    return stepBuilderFactory.get("step5")
        .flow(flow())
        .build();
  }

  @Bean
  public Job job(){
    return this.jobBuilderFactory.get("job")
        .start(step1())
        .next(step2())
        .next(step3())
        .build();
  }

  @Bean
  public Flow flow(){
    FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("flow");
    flowFlowBuilder.start(step2()).end();
    return flowFlowBuilder.build();
  }

}
