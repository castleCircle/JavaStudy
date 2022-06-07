package hello.aop.examtest;

import hello.aop.examtest.annotation.TraceTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamTestService {

  private final ExamTestRepository examTestRepository;

  @TraceTest
  public void save(String itemId){
    examTestRepository.save(itemId);
  }

}
