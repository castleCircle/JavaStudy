package wony20240204;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public class BankAccount {

  private final ReadWriteLock lock;
  private Map<String,Integer> balance = new HashMap<>();

  public BankAccount(ReadWriteLock lock) {
    this.lock = lock;
    balance.put("account1",0);
  }

  public int getBalance(){
    lock.readLock().lock();
    try{
      Thread.sleep(1000);
     return balance.get("account1");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.readLock().unlock();
    }
  }

  public void deposit(int amount){
    lock.writeLock().lock();
    try{
      Thread.sleep(2000);
      int currentBalance = balance.get("account1");
      currentBalance += amount;
      balance.put("account1",currentBalance);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.writeLock().unlock();
    }
  }


}
