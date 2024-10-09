package io.start;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

  public static void main(String[] args) throws IOException {
    FileOutputStream fos = new FileOutputStream("temp/hello.dat");
    //text문서가 읽을때 acsii 코드로 디코딩해서 보여주기 때문에 ABC로 보인다. 저장은 65,66,67이 맞다.
    fos.write(65);
    fos.write(66);
    fos.write(67);
    fos.close();

    final FileInputStream fis = new FileInputStream("temp/hello.dat");
    System.out.println(fis.read());
    System.out.println(fis.read());
    System.out.println(fis.read());
    System.out.println(fis.read());// -1 파일의 끝을 말함
    fis.close();
  }

}
