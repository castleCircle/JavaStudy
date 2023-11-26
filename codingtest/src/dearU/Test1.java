package dearU;

public class Test1 {

  public static void main(String[] args) throws InterruptedException {

    TestA a = new TestA();

    for(int i=0;i<50000;i++){

      int finalI = i;
      Thread thread1;

      thread1 = new Thread(() -> {
        a.run("thread" + finalI);
      });

//      if(i%2==0) {
//        thread1 = new Thread(() -> {
//          a.run("thread" + finalI);
//        });
//      }else{
//        thread1 = new Thread(() -> {
//          a.run1("thread" + finalI);
//        });
//      }
      thread1.start();
    }

//    Collections.synchronizedList()

  }


}
