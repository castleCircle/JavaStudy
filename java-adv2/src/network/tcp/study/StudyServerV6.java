package network.tcp.study;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StudyServerV6 {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket();

    while (true) {
      final Socket socket = serverSocket.accept();

    }
  }

}
