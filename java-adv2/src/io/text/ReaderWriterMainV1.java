package io.text;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReaderWriterMainV1 {

  public static void main(String[] args) throws IOException {
    String writeString = "ABC";

    //문자 - byte UTF-8 인코드이
    byte[] writeBytes = writeString.getBytes(UTF_8);
    System.out.println("write string = " + writeString);
    System.out.println("write Bytes = " + Arrays.toString(writeBytes));

    //파일에 쓰기
    final FileOutputStream fos = new FileOutputStream(FILE_NAME);
    fos.write(writeBytes);
    fos.close();

    //파일에서 읽기
    FileInputStream fis = new FileInputStream(FILE_NAME);
    byte[] readBytes = fis.readAllBytes();
    fis.close();

    //byte -> String UTF-8 디코딩
    String readString = new String(readBytes, UTF_8);

    System.out.println("read bytes = " + Arrays.toString(readBytes));
    System.out.println("read String = " + readString);
  }

}