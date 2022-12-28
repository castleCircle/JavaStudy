package com.example.springbatch;

public class CustomerService<T> {

  private int cnt = 0;

  public T customerRead(){
    return (T)("item" + cnt++);
  }

}
