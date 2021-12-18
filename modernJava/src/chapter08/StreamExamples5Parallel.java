package chapter08;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamExamples5Parallel {

  public static void main(String[] args) {

    int[] sum = {0};

    IntStream.range(0,100).forEach(i -> sum[0] += i) ;

    System.out.println("sum : " + sum[0]);

    int[] sum2 = {0};

    IntStream.range(0,100)
        .parallel()
        .forEach(i -> sum2[0] += i) ;

    System.out.println("parallel sum : " + sum2[0]);

    System.out.println("stream sum :" + IntStream.range(0,100).sum());

    System.out.println("stream parallel sum :" + IntStream.range(0,100).parallel().sum());

    final long start = System.currentTimeMillis();
    System.out.println("stream");
//    Arrays.asList(1,2,3,4,5,6,7,8)
//        .stream()
//        .map(i->{
//          try{
//            TimeUnit.SECONDS.sleep(1);
//          }catch (InterruptedException e){
//            e.printStackTrace();
//          }
//          return i;
//        })
//        .forEach(i-> System.out.println(i));
//    System.out.println(System.currentTimeMillis()-start);

    final long start2 = System.currentTimeMillis();
    System.out.println("parallem stream 9element");
    Arrays.asList(1,2,3,4,5,6,7,8,9)
        .parallelStream()
        .map(i->{
          try{
            TimeUnit.SECONDS.sleep(1);
          }catch (InterruptedException e){
            e.printStackTrace();
          }
          return i;
        })
        .forEach(i-> System.out.println(i));
    System.out.println(System.currentTimeMillis()-start2);

    System.out.println("parallem stream core setting");
//    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");
    final long start3 = System.currentTimeMillis();
    Arrays.asList(1,2,3,4,5,6,7,8)
        .parallelStream()
        .map(i->{
          try{
            TimeUnit.SECONDS.sleep(1);
          }catch (InterruptedException e){
            e.printStackTrace();
          }
          return i;
        })
        .forEach(i-> System.out.println(i));
    System.out.println(System.currentTimeMillis()-start3);

    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");
    System.out.println("core3");
    final long start4 = System.currentTimeMillis();
    Arrays.asList(1,2,3,4,5,6,7,8)
        .parallelStream()
        .map(i->{
          try{
            TimeUnit.SECONDS.sleep(1);
          }catch (InterruptedException e){
            e.printStackTrace();
          }
          return i;
        })
        .forEach(i-> System.out.println(i));
    System.out.println(System.currentTimeMillis()-start4);



  }

}
