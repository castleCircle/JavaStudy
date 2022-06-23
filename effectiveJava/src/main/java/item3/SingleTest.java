package item3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleTest {

  public static void main(String[] args)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {

    final String name = Singleton3.INSTANCE.getName();

    System.out.println(name);

  }


}
