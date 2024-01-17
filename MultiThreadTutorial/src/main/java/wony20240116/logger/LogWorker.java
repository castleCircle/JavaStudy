package wony20240116.logger;

import wony20240116.logger.ThreadLocalLogger.ServiceA;
import wony20240116.logger.ThreadLocalLogger.ServiceB;
import wony20240116.logger.ThreadLocalLogger.ServiceC;

public class LogWorker implements Runnable{

  @Override
  public void run() {
    ThreadLocalLogger.ServiceA serviceA = new ServiceA();
    ThreadLocalLogger.ServiceB serviceB = new ServiceB();
    ThreadLocalLogger.ServiceC serviceC = new ServiceC();

    if(Thread.currentThread().getName().equals("Thread-1")){
      serviceA.process();
      serviceB.process();
      serviceC.process();
    }else if(Thread.currentThread().getName().equals("Thread-2")){
      serviceB.process();
      serviceA.process();
      serviceC.process();
    }else{
      serviceC.process();
      serviceA.process();
      serviceB.process();
    }

    ThreadLocalLogger.printLog();
    ThreadLocalLogger.clearLog();

  }
}
