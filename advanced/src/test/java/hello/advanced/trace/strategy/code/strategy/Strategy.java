package hello.advanced.trace.strategy.code.strategy;

/**
 * 전략패턴은 알고리즘 제품군을 정의하고 각각을 캡슐화 하여 상호 교환 가능하게 만들자. 전략을 사용하면 알고리즘을 사용하는 클라이언트와 독립적으로 알고리즘을 변경할수 있다.
 */
public interface Strategy {
  void call();
}
