package thread.study;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

  private int balance;

  public BankAccountV2(int balance) {
    this.balance = balance;
  }

  @Override
  public synchronized boolean withdraw(int amount) {
    log("거래시작: " + getClass().getSimpleName());

    log("[검증 시작] 출금액"  + amount + ", 잔액:" + balance);
    if(balance < amount){
      log("[검증 실페] 출금액: " + amount + ", 잔액: " + balance );
      return false;
    }

    log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
    sleep(1000);
    balance -= amount;
    log("[출금 완료] 출금액: " + amount + ", 변경 잔액: " + balance);

    return true;
  }

  @Override
  public int getBalance() {
    return balance;
  }
}
