package thread.control.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountV6 implements BankAccount{

  private int balance;

  private final Lock lock = new ReentrantLock();

  public BankAccountV6(int balance) {
    this.balance = balance;
  }

  @Override
  public boolean withdraw(int amount) {
    log("거래 시작: " + getClass().getSimpleName());
    //잔고가 출금액 보다 적으면, 진행하면 안됨

    //===임계 영역 시작==

    try {
      if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
        log("[진입실패] 이미 처리중인 작업이 있습니다.");
        return false;
      }
    }catch (InterruptedException e){
      throw new RuntimeException(e);
    }

    
    try {
      log("[검증시작] 출금액: " + amount + ", 잔액: " + balance);
      if (balance < amount) {
        log("[검증실패] 출금액: " + amount + ", 잔액:" + balance);
        return false;
      }

      //잔고가 출금액 보다 많으면 진행
      log("[검증완료] 출금액: " + amount + ", 잔액:" + balance);
      sleep(1000);
      balance = balance - amount;
      log("[출금완] 출금액: " + amount + ", 잔액:" + balance);
      //===임계 영역 종료==
    }finally {
      lock.unlock();
    }
    log("거래 종료: " + getClass().getSimpleName());
    return true;
  }

  @Override
  public int getBalance() {
    lock.lock();
    try {
      return balance;
    }finally {
      lock.unlock();
    }
  }
}
