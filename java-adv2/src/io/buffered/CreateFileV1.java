package io.buffered;

import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 약 14초 ㅋㅋ 그 이상 걸린다.
 */
public class CreateFileV1 {

  public static void main(String[] args) throws IOException {
    final FileOutputStream fos = new FileOutputStream(FILE_NAME);
    long startTime = System.currentTimeMillis();
    System.out.println("tte");
    for(int i=0;i<FILE_SIZE ;i++){
      fos.write(1);
    }
    fos.close();

    long endTime = System.currentTimeMillis();

    System.out.println("File created: " + FILE_NAME);
    System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
    System.out.println("Time taken: " + (endTime - startTime) + "ms");
  }

}