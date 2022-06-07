package hello.aop.examtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ExamTestRepository {

  public void save(String itemId){
    log.info("repository:{}",itemId);
  }

}
