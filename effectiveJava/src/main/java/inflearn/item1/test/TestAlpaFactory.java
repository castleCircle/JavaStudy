package inflearn.item1.test;

import java.util.Optional;
import java.util.ServiceLoader;

public class TestAlpaFactory {

  public static void main(String[] args) {
    ServiceLoader<TestAlpa> loader = ServiceLoader.load(TestAlpa.class);
    final Optional<TestAlpa> first = loader.findFirst();
  }

}
