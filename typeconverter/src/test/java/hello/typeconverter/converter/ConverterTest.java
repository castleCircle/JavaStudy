package hello.typeconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

  @Test
  void stringToInteger(){
    final StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
    Integer result = stringToIntegerConverter.convert("10");
    assertThat(result).isEqualTo(10);
  }

  @Test
  void IntegerToString(){
    final IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
    String result = integerToStringConverter.convert(10);
    assertThat(result).isEqualTo("10");
  }

  @Test
  void portToString(){
    final IpPortToStringConverter converter = new IpPortToStringConverter();
    final IpPort ipPort = new IpPort("127.0.0.1", 8080);
    String result = converter.convert(ipPort);
    assertThat(result).isEqualTo("127.0.0.1:8080");
  }

  @Test
  void StringToIpPort(){
    final StringToIpPortConverter converter = new StringToIpPortConverter();
    String source = "127.0.0.1:8080";
    final IpPort convert = converter.convert(source);
    assertThat(convert).isEqualTo(new IpPort("127.0.0.1",8080));
  }

}
