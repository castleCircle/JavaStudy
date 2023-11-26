package com.example.springbatchtest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChunkJob {

  private final JobBuilderFactory jobBuilderFactory;

  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job chunkBasedJobs(){
    return this.jobBuilderFactory.get("chunkBasedJob")
        .start(chunkStep())
        .on()
        .incrementer(new DailyJobTimeStamper())
        .build();
  }

  @Bean
  public Step chunkStep(){
    return this.stepBuilderFactory.get("chunkStep")
        .<String,String>chunk(1000)
        .reader(itemReader())
        .writer(itemWriter())
        .build();
  }

  @Bean
  public ListItemReader<String> itemReader(){

    List<String> items = new ArrayList<>(10000);

    for(int i=0;i<10000;i++){
      items.add(UUID.randomUUID().toString());
    }

    System.out.println("itemReader Finish");
    return new ListItemReader<>(items);
  }

  @Bean
  public ItemWriter<String> itemWriter(){
    return items -> {
      System.out.println("items count" + items.size());

      BatchStatus.COMPLETED;

      ExitStatus.COMPLETED;
      for(String item : items){
//        System.out.println(">> current item = " + item);
      }
    };
  }

}
