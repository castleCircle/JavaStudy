package io.start;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PrintStreamMain {

  public static void main(String[] args) throws IOException {
    System.out.println("hello");

    PrintStream printStream = System.out;

    final byte[] bytes = "Hello!\n".getBytes(StandardCharsets.UTF_8);
    printStream.write(bytes);
    printStream.println("Print!");
  }

}
