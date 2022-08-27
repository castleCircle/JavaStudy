package com.example.springbatch;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChunkConfiguration {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job batchJob() {
    return jobBuilderFactory.get("batchJob")
        .start(step1())
        .next(step2())
        .incrementer(new RunIdIncrementer())
        .listener(new JobListener())
        .build();
  }


  @Bean
  public Step step1(){
    return stepBuilderFactory.get("step1")
        .<String,String>chunk(5)
        .reader(new ListItemReader<>(Arrays.asList("item1","item2","item3","item4","item5")))
        .processor(new ItemProcessor<String, String>() {
          @Override
          public String process(String item) throws Exception {
            Thread.sleep(300);
            System.out.println("item="+item);
            return "my" + item;
          }
        })
        .writer(new ItemWriter<String>() {
          @Override
          public void write(List<? extends String> items) throws Exception {
            Thread.sleep(300);
            System.out.println(items);
          }
        })
        .build();
  }

  @Bean
  public Step step2(){
    return stepBuilderFactory.get("step2")
        .<String,String>chunk(3)
        .reader(new ListItemReader<>(Arrays.asList("age1","age2","age3","age4","age5")))
        .processor(new ItemProcessor<String, String>() {
          @Override
          public String process(String item) throws Exception {
            return "my" + item;
          }
        })
        .writer(new ItemWriter<String>() {
          @Override
          public void write(List<? extends String> items) throws Exception {
            System.out.println(items);
          }
        })
        .build();
  }


}
