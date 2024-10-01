package sample.cafekiosk.spring.api.service.product;

import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

/**
 * readOnly = true : 읽기전용
 * CRUD 에서 CUD 동작 X / Only Read
 * JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
 *
 * CQRS - COMMAND / READ
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  //동시성 이슈 고민해야함
  @Transactional
  public ProductResponse createProduct(ProductCreateServiceRequest request) {
    // productNumber
    // 001 002 003 004
    // DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
    // 009 -> 010
    final String nextProductNumber = createNextProductNumber();

    Product product = request.toEntity(nextProductNumber);
    Product savedProduct = productRepository.save(product);

    return ProductResponse.of(savedProduct);
  }

  private String createNextProductNumber() {
    String latestProductNumber = productRepository.findLatestProductNumber();
    if(latestProductNumber == null) {
      return "001";
    }

    final int latestProductNumberInt= Integer.parseInt(latestProductNumber);
    int nextProductNumberInt = latestProductNumberInt + 1;
    
    return String.format("%03d",nextProductNumberInt);
  }

  public List<ProductResponse> getSellingProducts(){
    final List<Product> products = productRepository.findAllBySellingStatusIn(
        ProductSellingStatus.forDisplay());

    return products.stream()
        .map(ProductResponse::of)
        .collect(Collectors.toList());

  }


}
