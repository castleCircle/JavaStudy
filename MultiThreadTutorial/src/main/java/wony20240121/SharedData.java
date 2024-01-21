package wony20240121;

public class SharedData {

  private int sharedValue = 0;

  private Mutex mutex;

  public SharedData(Mutex mutex){
    this.mutex = mutex;
  }

  public void sum(){
    mutex.acquired();
    try{
      for(int i=0;i<1000000;i++){
        sharedValue++;
      }
    }catch (Exception e){

    }finally {
      mutex.release();
    }
  }

  public int getSum(){
    return sharedValue;
  }

}
