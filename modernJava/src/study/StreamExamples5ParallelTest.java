package study;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class StreamExamples5ParallelTest {

  public static void main(String[] args) {
//
//    final var start = System.currentTimeMillis();
//    Arrays.asList(1,2,3,4,5,6,7,8)
//        .stream()
//        .map(i->{
//          try {
//            System.out.println("test1");
//            TimeUnit.SECONDS.sleep(1);
//          }catch (Exception e){
//
//          }
//          return i;
//        })
//        .forEach(i-> System.out.println(i)
//        );
//
//    System.out.println(System.currentTimeMillis()-start);
//
//
//    final var start2 = System.currentTimeMillis();
//    Arrays.asList(1,2,3,4,5,6,7,8)
//        .parallelStream()
//        .map(i->{
//          try {
//            System.out.println("test1");
//            TimeUnit.SECONDS.sleep(1);
//          }catch (Exception e){
//
//          }
//          return i;
//        })
//        .forEach(i-> System.out.println(i)
//        );
//
//    System.out.println(System.currentTimeMillis()-start2);

    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");
    final var start3 = System.currentTimeMillis();
    Arrays.asList(1,2,3,4,5,6,7,8)
        .parallelStream()
        .map(i->{
          try {
            System.out.println("test1");
            TimeUnit.SECONDS.sleep(1);
          }catch (Exception e){

          }
          return i;
        })
        .forEach(i-> System.out.println(i)
        );

    System.out.println(System.currentTimeMillis()-start3);

  }

}
