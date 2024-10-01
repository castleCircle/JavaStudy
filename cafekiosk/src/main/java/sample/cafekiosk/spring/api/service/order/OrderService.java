package sample.cafekiosk.spring.api.service.order;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;
import sample.cafekiosk.spring.domain.stock.Stock;
import sample.cafekiosk.spring.domain.stock.StockRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final StockRepository stockRepository;

  public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) {
    final List<String> productNumbers = request.getProductNumbers();
    final List<Product> products = findProductsBy(productNumbers);

    deductStockQuantities(products);

    final Order order = Order.create(products, registeredDateTime);
    final Order savedOrder = orderRepository.save(order);
    return OrderResponse.of(savedOrder);
  }

  private void deductStockQuantities(List<Product> products) {
    //재고 차감 체크가 필요한 상품들 filter
    final List<String> stockProductNumbers = extractStockProductNumbers(products);

    //재고 엔티티 조회
    final Map<String, Stock> stockMap = createStockMapBy(stockProductNumbers);

    //상품별 counting
    final Map<String, Long> productCountingMap = createCountingMapBy(stockProductNumbers);

    //재고 차감 시도
    for(String stockProductNumber : new HashSet<>(stockProductNumbers)){
      Stock stock = stockMap.get(stockProductNumber);
      int quantity = productCountingMap.get(stockProductNumber).intValue();
      //deductQuantity에서도 체크를 하는데 왜 여기서 하나
      //stock에서 하는건 범용적인거고 ㅅ ㅓ비스에서 하는건 클라이언트에게 다른 메세지를 보내고 싶을수 있으니까
      if(stock.isQuantityLessThan(quantity)){
        throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
      }
      stock.deductQuantity(quantity);
    }
  }

  private static Map<String, Long> createCountingMapBy(List<String> stockProductNumbers) {
    return stockProductNumbers.stream()
        .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
  }

  private Map<String, Stock> createStockMapBy(List<String> stockProductNumbers) {
    final List<Stock> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);
    final Map<String, Stock> stockMap = stocks.stream()
        .collect(Collectors.toMap(Stock::getProductNumber, s -> s));
    return stockMap;
  }

  private static List<String> extractStockProductNumbers(List<Product> products) {
    List<String> stockProductNumbers = products.stream()
        .filter(product -> ProductType.containsStockType(product.getType()))
        .map(Product::getProductNumber)
        .collect(Collectors.toList());
    return stockProductNumbers;
  }

  private List<Product> findProductsBy(List<String> productNumbers) {
    final List<Product> products = productRepository.findAllByProductNumberIn(
        productNumbers);

    final Map<String, Product> productMap = products.stream()
        .collect(Collectors.toMap(product -> product.getProductNumber(), product -> product));

    final List<Product> duplicateProducts = productNumbers.stream()
        .map(productMap::get)
        .collect(Collectors.toList());
    return duplicateProducts;
  }
}
