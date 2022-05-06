package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

/**
 * 템플릿 메서드 패터은 단점이 부모가 바뀌면 하위 클래스는 영향을 받을수 밖에 없다.
 * 하위 클래스는 일부만 재정의 해서 쓰는데 그에 반해 너무 무겁다.
 * 그것의 단점을 위해 나온것이 전략패턴이다.
 * @param <T>
 */
public abstract class AbstractTemplate<T> {

  private final LogTrace trace;

  public AbstractTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public T execute(String message){
    TraceStatus status = null;
    try{
      status = trace.begin(message);

      T result = call();
      trace.end(status);
      return  result;
    }catch (Exception e){
      trace.exception(status,e);
      throw e;
    }
  }

  protected abstract T call();
}
