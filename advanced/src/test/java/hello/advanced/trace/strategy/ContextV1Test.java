package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.AbstractTemplateTest;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

  @Test
  void strategyV0(){
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    log.info("비지니스 로직1 실행");

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTiem={}",resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    log.info("비지니스 로직2 실행");

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTiem={}",resultTime);
  }

  @Test
  void templateMethodV1(){
    AbstractTemplate template1 = new SubClassLogic1();
    template1.execute();

    AbstractTemplate template2 = new SubClassLogic2();
    template2.execute();
  }

  @Test
  void templateMethodV2(){
    AbstractTemplate template1 = new AbstractTemplate() {

      @Override
      protected void call() {
        log.info("비지니스 로직1 실행");
      }
    };
    template1.execute();

    AbstractTemplate template2 = new AbstractTemplate() {

      @Override
      protected void call() {
        log.info("비지니스 로직1 실행");
      }
    };
    template2.execute();
  }

  @Test
  void strategyV1(){
    final StrategyLogic1 strategy1 = new StrategyLogic1();
    ContextV1 contextV1 = new ContextV1(strategy1);
    contextV1.execute();

    final StrategyLogic2 strategy2 = new StrategyLogic2();
    ContextV1 contextV2 = new ContextV1(strategy2);
    contextV2.execute();
  }
}
