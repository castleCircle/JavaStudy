package charset;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodingMain1 {

  private static final Charset EUC_KR = Charset.forName("EUC-KR");
  private static final Charset MS_949 = Charset.forName("MS949");

  public static void main(String[] args) {
    System.out.println("== ASCII 영문 처리 ==");
    encoding("A", US_ASCII);
    encoding("A", ISO_8859_1);
    encoding("A", EUC_KR);
    encoding("A", UTF_8);
    encoding("A", UTF_16BE); //얘는 호환이 안된다.

    System.out.println("== 한글 지원 ==");
    encoding("가",EUC_KR);
    encoding("가",MS_949);
    encoding("가", UTF_8);
    encoding("가", UTF_16BE);

    //모든 문자를 byte로 바꿀때는 문자집합을 지정해줘야함. 보토 안해주면 기본값으로 세팅되기 때문에 가능
  }

  private static void encoding(String text, Charset charset) {
    byte[] bytes = text.getBytes(charset);
    System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset,
        Arrays.toString(bytes), bytes.length);
  }

}
