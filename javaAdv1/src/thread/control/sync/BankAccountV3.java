package thread.control.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount{

  private int balance;

  public BankAccountV3(int balance) {
    this.balance = balance;
  }

  @Override
  public boolean withdraw(int amount) {
    log("거래 시작: " + getClass().getSimpleName());
    //잔고가 출금액 보다 적으면, 진행하면 안됨

    //===임계 영역 시작==
    synchronized (this) {
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
    }

    log("거래 종료: " + getClass().getSimpleName());
    return true;
  }

  @Override
  public synchronized int getBalance() {
    return balance;
  }
}