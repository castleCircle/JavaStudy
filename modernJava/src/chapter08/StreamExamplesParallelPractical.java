package chapter08;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamplesParallelPractical {

  private static final String[] priceString = {"1.0","100.99","35.75","21.30","88.00"};
  private static final BigDecimal[] targetPRices = {new BigDecimal("30"),new BigDecimal("20"),
      new BigDecimal("31")};
  private static final Random random = new Random(123);
  private static final Random targetPriceRandom = new Random(111);
  private static final List<Product1> products;

  static {
    final int length = 8_000_000;
    final Product1[] list = new Product1[length];
    for(int i=1;i<=length;i++){
      list[i-1] =(new Product1((long)i,"Product"+i,new BigDecimal(priceString[random.nextInt(5)])));
    }
    products = Arrays.asList(list);
  }

  private static BigDecimal imperativeSum(final List<Product1> products, final Predicate<Product1> predicate){
    BigDecimal sum = BigDecimal.ZERO;
    for(final Product1 product : products){
      if(predicate.test(product)){
        sum = sum.add(product.getPrice());
      }
    }
    return sum;
  }

  private static BigDecimal streamSum(final Stream<Product1> stream, final Predicate<Product1> predicate){
    return stream.filter(predicate).map(Product1::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
  }

  private static void imperativeTest(BigDecimal targetPrice){
    System.out.println("===================");
    System.out.println("\nImperative Sum\n-------------------------");
    final long start = System.currentTimeMillis();
    System.out.println("Sum: " + imperativeSum(products,product1 -> product1.getPrice().compareTo(targetPrice) >= 0));
    System.out.println("It took " + (System.currentTimeMillis() - start) + "ms.");
    System.out.println("===================");
  }

  private static void streamTest(BigDecimal targetPrice){
    System.out.println("===================");
    System.out.println("\nStream Sum\n-------------------------");
    final long start = System.currentTimeMillis();
    System.out.println("Sum: " + streamSum(products.stream(),product1 -> product1.getPrice().compareTo(targetPrice) >= 0));
    System.out.println("It took " + (System.currentTimeMillis() - start) + "ms.");
    System.out.println("===================");
  }

  private static void parallelStreamTestTest(BigDecimal targetPrice){
    System.out.println("===================");
    System.out.println("\nparallelStream Sum\n-------------------------");
    final long start = System.currentTimeMillis();
    System.out.println("Sum: " + streamSum(products.parallelStream(),product1 -> product1.getPrice().compareTo(targetPrice) >= 0));
    System.out.println("It took " + (System.currentTimeMillis() - start) + "ms.");
    System.out.println("===================");
  }

  public static void main(String[] args) {
    final BigDecimal targetPrice = new BigDecimal("40");
    imperativeTest(targetPrice);
    streamTest(targetPrice);
    parallelStreamTestTest(targetPrice);

    System.out.println("\nIgnore Test Above\n====================\n");
    System.out.println("start!");
    for(int i=0;i<5;i++){
      BigDecimal price = targetPRices[targetPriceRandom.nextInt(3)];

      imperativeTest(price);
      streamTest(price);
      parallelStreamTestTest(price);
    }
  }

}


@AllArgsConstructor
@Data
class Product1{
  private Long id;
  private String name;
  private BigDecimal price;
}