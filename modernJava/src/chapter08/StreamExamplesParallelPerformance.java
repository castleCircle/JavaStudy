package chapter08;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExamplesParallelPerformance {

  private static void slowDown(){
    try {
      TimeUnit.MICROSECONDS.sleep(10L);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public static long iterativeSum(long n){
    long result  = 0;
    for(long i=0;i<=n;i++){
      result +=i;
      slowDown();
    }
    return result;
  }

  public static long sequentialSum(long n){
    return Stream.iterate(1L,i->i+1).limit(n).peek(i-> slowDown()).reduce(Long::sum).get();
  }

  public static long parallelSum(long n){
    return Stream.iterate(1L,i->i+1).limit(n).peek(i->slowDown()).parallel().reduce(Long::sum).get();
  }

  public static long rangedSum(long n){
    return LongStream.rangeClosed(1,n).peek(i->slowDown()).reduce(Long::sum).getAsLong();
  }

  public static long parallelRangedSum(long n){
    return LongStream.rangeClosed(1,n).parallel().reduce(Long::sum).getAsLong();
  }

  public static void main(String[] args) {
    final long n = 1000;
    final long start = System.currentTimeMillis();

    final long start1 = System.currentTimeMillis();
    System.out.println(iterativeSum(n));
    System.out.println(System.currentTimeMillis()-start1);

    final long start2 = System.currentTimeMillis();
    System.out.println(sequentialSum(n));
    System.out.println(System.currentTimeMillis()-start2);

    final long start3 = System.currentTimeMillis();
    System.out.println(parallelSum(n));
    System.out.println(System.currentTimeMillis()-start3);


    final long start4 = System.currentTimeMillis();
    System.out.println(rangedSum(n));
    System.out.println(System.currentTimeMillis()-start4);

    final long start5 = System.currentTimeMillis();
    System.out.println(parallelRangedSum(n));
    System.out.println(System.currentTimeMillis()-start5);
  }

}
