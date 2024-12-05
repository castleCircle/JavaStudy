package thread.study;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Study {

  public static void main(String[] args) throws InterruptedException {
    Printer printer = new Printer();
    Thread printerThread = new Thread(printer,"printer");
    printerThread.start();

    Scanner userInput = new Scanner(System.in);
    while(true){
      log("프린트할 문서를 입력하세요. 종료 (q): ");
      String input = userInput.nextLine();
      if(input.equals("q")){
        printerThread.interrupt();
        break;
      }

      printer.addJob(input);
    }
  }

  static class Printer implements Runnable{
    Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
      while(!Thread.interrupted()){
        if(jobQueue.isEmpty()){
          Thread.yield();
          continue;
        }

        String job = jobQueue.poll();
        log("출력 시작: " + job + ". 대기문서:" + jobQueue);
        try {
          sleep(3000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log("출력 완료: " + job);
      }
      log("프린터 종료");
    }

    public void addJob(String input){
      jobQueue.offer(input);
    }
  }



}
