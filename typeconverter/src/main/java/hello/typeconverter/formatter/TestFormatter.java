package hello.typeconverter.formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

@Slf4j
public class TestFormatter implements Formatter<Number> {

  @Override
  public Number parse(String text, Locale locale) throws ParseException {
    final NumberFormat instance = NumberFormat.getInstance(locale);
    return instance.parse(text);
  }

  @Override
  public String print(Number object, Locale locale) {
    return NumberFormat.getInstance().format(object);
  }
}
