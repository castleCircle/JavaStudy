package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

  @Test
  void begin_end(){
    HelloTraceV2 trace = new HelloTraceV2();
    TraceStatus status = trace.begin("hello");
    final TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
    trace.end(status1);
    trace.end(status);
  }

  @Test
  void begin_exception(){
    HelloTraceV2 trace = new HelloTraceV2();
    TraceStatus status = trace.begin("hello");
    final TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
    trace.exception(status1,new IllegalStateException());
    trace.exception(status,new IllegalStateException());
  }

  @Test
  void test(){
    HelloTraceV2 trace = new HelloTraceV2();
    final TraceStatus start1 = trace.begin("start1");
    final TraceStatus start2 = trace.beginSync(start1.getTraceId(), "start2");
    trace.end(start2);
    trace.end(start1);
  }

}
