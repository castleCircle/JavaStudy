package network.tcp.v4;

import static util.MyLogger.log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import network.tcp.SocketCloseUtil;

public class SessionV4 implements Runnable{

  private final Socket socket;

  public SessionV4(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {

    DataInputStream input = null;
    DataOutputStream output = null;

    try {
      input = new DataInputStream(socket.getInputStream());
      output = new DataOutputStream(socket.getOutputStream());

      while(true){
        final String received = input.readUTF();
        log("client -> server: " + received);

        if(received.equals("exit")){
          break;
        }

        //클라이언트에게 문자 보내기
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);
      }
    }catch (IOException e) {
      log(e);
    }finally {
      SocketCloseUtil.closeAll(socket,input,output);
      log("연결 종료: " + socket);
    }
  }
}
