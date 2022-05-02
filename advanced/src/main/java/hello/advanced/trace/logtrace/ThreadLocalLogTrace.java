package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{

  //ThreadLocal은 주의해야 한다.
  //다 사용하면 remove를 해줘야 한다.
  //ThreadLocal은 해당 쓰레드에 데이터를 기록한다.
  //그렇기 때문에 ThreadLocal을 사용한 데이터는 동시성 문제가 일어나지 않는다.
  //그러나 다 사용후 지우지 않는다면 문제가 생길수 있다.
  //why? WAS는 Threadpool을 사용하기 때문이다.
  //처음에 A라는 사람의 요청이 들어왔다고 치자. 그러면 A의 요청에 WAS는 threadPool에 있는  thread하나를 꺼내서 그곳에서 프로그램을 돌린다.
  //그리고 threadLocal을 사용한 코드가 있었고 그곳에 set을 했고 요청이 끝났다.
  //결과적으로는 문제가 없는것 처럼 보인다.
  //하지만 WAS는 사용한 thread를 버리는게 아니라 반납한 다음 다른 요청이 있을때 재사용한다.
  //A가 아닌 다른 사람 혹은 A 사람이라고 하더라도 처음에 A라는 사람이 사용했던 thread를 할당받고 거기서 ThreadLocal을 사용한 코드중 Set을 했던 코드가 있으면
  //기존에 A의 요청에 의한 작동했던 코드들에 의해 남았던 데이터가 문제를 발생 시킬수 있다.
  //그러므로 요청에 대해 끝났을 경우에는 ThreadLocal을 반드시 remove해줘야 한다.

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

  @Override
  public TraceStatus begin(String message) {
    syncTraceId();
    TraceId traceId = traceIdHolder.get();
    Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
        traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  private void syncTraceId(){
    TraceId traceId = traceIdHolder.get();
    if(traceId == null){
      traceIdHolder.set(new TraceId());
    }else{
      traceIdHolder.set(traceId.createNextId());
    }
  }

  @Override
  public void end(TraceStatus status) {
    complete(status, null);
  }

  @Override
  public void exception(TraceStatus status, Exception e) {
    complete(status, e);
  }

  private void complete(TraceStatus status, Exception e) {
    Long stopTimeMs = System.currentTimeMillis();
    long resultTimeMs = stopTimeMs - status.getStartTimeMs();
    TraceId traceId = status.getTraceId();
    if (e == null) {
      log.info("[{}] {}{} time={}ms", traceId.getId(),
          addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
          resultTimeMs);
    } else {
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
          addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
          e.toString());
    }

    releaseTraceId();
  }

  private void releaseTraceId() {
    final TraceId traceId = traceIdHolder.get();
    if(traceId.isFirstLevel()){
      traceIdHolder.remove();
    }else{
      traceIdHolder.set(traceId.createPreviousId());
    }
  }

  private static String addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append( (i == level - 1) ? "|" + prefix : "|   ");
    }
    return sb.toString();
  }
}
