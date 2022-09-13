package inflearn.item1;

import java.util.Optional;
import java.util.ServiceLoader;

public class HelloServiceFactory {

  public static void main(String[] args) {

    //이러한것은 차이니즈 서비스에 의존하지 않기 때문에
    ServiceLoader<HelloService> loader = ServiceLoader.load(HelloService.class);
    final Optional<HelloService> helloServiceOptional = loader.findFirst();

    helloServiceOptional.ifPresent(h-> System.out.println(h.hello()));
  }

}
