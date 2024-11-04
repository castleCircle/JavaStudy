package network.tcp.v6;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientV6 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("클라이언트 시작");

    try (
        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
    ) {
      log("소켓 연결: " + socket);

      Scanner scanner = new Scanner(System.in);
      while (true) {
        System.out.println("전송 문자:");
        String toSend = scanner.nextLine();

        output.writeUTF(toSend);
        log("client -> server: " + toSend);

        if (toSend.equals("exit")) {
          break;
        }

        //서버로부터 문자 받기
        String received = input.readUTF();
        log("client <- server: " + received);
      }
    } catch (IOException e) {
      System.out.println("서버에서 죽음");
      log(e);
    }
  }

}
