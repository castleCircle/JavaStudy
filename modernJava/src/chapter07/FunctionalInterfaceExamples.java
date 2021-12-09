package chapter07;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamples {

  public static void main(String[] args) {
    final List<Product> products = Arrays.asList(
        new Product(1L,"A",new BigDecimal("10.00")),
        new Product(2L,"B",new BigDecimal("55.50")),
        new Product(3L,"C",new BigDecimal("17.45")),
        new Product(4L,"D",new BigDecimal("20.00")),
        new Product(5L,"E",new BigDecimal("110.99"))
    );

    final BigDecimal twenty = new BigDecimal("20");
    final List<Product> result = filter(products,product -> product.getPrice().compareTo(twenty) >= 0);
    System.out.println(result);

    final List<Product> expensiveProducts = filter(products,product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);
    final List<DiscountedProduct> discountedProducts = map(
        expensiveProducts,product ->
            new DiscountedProduct(product.getId(),product.getName(),product.getPrice().multiply(new BigDecimal("0.5"))));
    System.out.println("expensive: " +  expensiveProducts);
    System.out.println("discount : " +discountedProducts);

    final Predicate<Product> lessThanOfEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;
    System.out.println("discounted products : " + filter(discountedProducts,lessThanOfEqualTo30));
    System.out.println("           products : " + filter(products,lessThanOfEqualTo30));

    //56:44초
  }

  private static <T> List<T> filter(List<T> list , Predicate<? super T> predicate){
    final List<T> result = new ArrayList<>();
    for(final T t : list){
      if(predicate.test(t)){
        result.add(t);
      }
    }
    return result;
  }

  private static <T,R> List<R> map(List<T> list, Function<T,R> function){
    final List<R> discountedProducts = new ArrayList<>();
    for(final T t : list){
      discountedProducts.add(function.apply(t));
    }
    return discountedProducts;
  }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Product{
  private Long id;
  private String name;
  private BigDecimal price;

}


@ToString(callSuper = true)
class DiscountedProduct extends Product{
  public DiscountedProduct(Long id, String name, BigDecimal price) {
    super(id, name, price);
  }

  public DiscountedProduct() {
  }
}