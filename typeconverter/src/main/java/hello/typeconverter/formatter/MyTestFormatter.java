package hello.typeconverter.formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class MyTestFormatter implements Formatter<Number> {

  @Override
  public Number parse(String text, Locale locale) throws ParseException {
    return NumberFormat.getInstance(locale).parse(text);
  }

  @Override
  public String print(Number object, Locale locale) {
    return NumberFormat.getInstance(locale).format(object);
  }
}
