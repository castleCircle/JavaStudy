package item3;

import java.lang.reflect.InvocationTargetException;

public class SingleTest {

  public static void main(String[] args)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {

    final String name = Singleton3.INSTANCE.getName();

    System.out.println(name);

  }


}
