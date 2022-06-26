package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Job , Step은 bean으로 등록되기 때문에 중복되면 안됨 고유해야함
 * method가 기준이 되지만 .get() 이름또한 중복되면 안됨
 */

@RequiredArgsConstructor
@Configuration
@Slf4j
public class HelloJobConfiguration {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job helloJob(){
    return jobBuilderFactory.get("helloJob")
        .start(helloStep1())
        .next(helloStep2())
        .build();
  }

  @Bean
  public Step helloStep1() {
    return stepBuilderFactory.get("helloStep1")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
              throws Exception {

            log.info("========================");
            log.info(" >> Hello Spring Batch1!!");
            log.info("========================");

            return RepeatStatus.FINISHED;
          }
        })
        .build();
  }

  @Bean
  public Step helloStep2() {
    return stepBuilderFactory.get("helloStep1")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
              throws Exception {

            log.info("========================");
            log.info(" >> Hello Spring Batch2!!");
            log.info("========================");

            return RepeatStatus.FINISHED;
          }
        })
        .build();
  }
}
