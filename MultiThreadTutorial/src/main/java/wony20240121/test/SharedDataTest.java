package wony20240121.test;

public class SharedDataTest {

  private MutexTest mutexTest;

  private int count = 0;

  public SharedDataTest(MutexTest mutexTest){
    this.mutexTest =mutexTest;
  }

  public void start(){
    try{
      mutexTest.acquired();
      for(int i=0;i<1000000;i++){
       count++;
      }
    }catch (Exception e){

    }finally {
      mutexTest.release();
    }
  }

  public int getCount(){
    return count;
  }

}
