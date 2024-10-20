package io.file.copy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateCopyFile {

  private static final int FILE_SIZE = 200 * 1024 * 1024;

  public static void main(String[] args) throws IOException {
    String fileName = "temp/copy.dat";
    System.out.println();
    long startTime = System.currentTimeMillis();

    FileOutputStream fos = new FileOutputStream(fileName);
    byte[] buffer = new byte[FILE_SIZE];
    fos.write(buffer);
    fos.close();

    long endTime = System.currentTimeMillis();
    System.out.println("File Created: " + fileName);
    System.out.println("File Size: " + FILE_SIZE / 1024 / 1024 + "MB");
    System.out.println("Time Taken: " + (endTime - startTime) + "ms");
  }

}
