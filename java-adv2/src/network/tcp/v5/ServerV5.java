package network.tcp.v5;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerV5 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("서버 시작");
    final ServerSocket serverSocket = new ServerSocket(PORT);
    log("서버 소켓 시작 - 리스닝 포트: " + PORT);

    while (true) {
      Socket socket = serverSocket.accept(); // 블로킹
      log("소켓 연결: " + socket);

      final SessionV5 session = new SessionV5(socket);
      final Thread thread = new Thread(session);
      thread.start();
    }
  }

}
