package network.tcp.v3;

import static util.MyLogger.log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV3 {

  private static final int PORT = 12345;

  public static void main(String[] args) throws IOException {
    log("서버 시작");
    final ServerSocket serverSocket = new ServerSocket(PORT);
    log("서버 소켓 시작 - 리스닝 포트: " + PORT);

    while(true){
      Socket socket = serverSocket.accept(); // 블로킹
      log("소켓 연결: " + socket);

      final SessionV3 session = new SessionV3(socket);
      final Thread thread = new Thread(session);
      thread.start();
    }
  }

}