package network.tcp.study;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class StudyClientV6 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {

    try (
        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
    ) {

      log("소켓 연결");

      Scanner sc = new Scanner(System.in);
      while (true) {
        final String toSend = sc.nextLine();
        output.writeUTF(toSend);

        if (toSend.equals("exit")) {
          break;
        }

        final String received = input.readUTF();
        log("client <= send : " + received);
      }
    } catch (IOException e) {
      System.out.println("서버에서 죽임");
    }
  }

}
