package hello.typeconverter.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

class FormatterTest {

  TestFormatter formatter = new TestFormatter();

  @Test
  void parse() throws ParseException {
    final Number parse = formatter.parse("1,000", Locale.KOREA);
    assertThat(parse).isEqualTo(1000L);
  }

  @Test
  void print(){
    final String print = formatter.print(1000L, Locale.KOREA);
    assertThat(print).isEqualTo("1,000");
  }

  @Test
  void conversionTest(){
    DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
    defaultFormattingConversionService.addFormatter(new TestFormatter());

    assertThat(defaultFormattingConversionService.convert("1,000",Long.class)).isEqualTo(1000L);
  }

}