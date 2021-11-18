import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://yonguri.tistory.com/136

public class Main {

    private static String getRoomStatus(int roomNo){
      try{
          System.out.println("Called Room: " + roomNo);
          Thread.sleep(3000);
      }catch(InterruptedException e){
          e.printStackTrace();
      }
      return roomNo + ":empty";
    }

    public static void main(String[] args) {

        List<Map<Integer,String>> roomStatusList = new ArrayList<>();

        int roomCount = 10;
        for(int roomNo = 1; roomNo <= roomCount; roomNo++){
            String roomStatus = getRoomStatus(roomNo);
            System.out.println("roomNo : " + roomNo + ", roomStatus : " + roomStatus);

            Map<Integer,String> statusMap = new HashMap<>();
            statusMap.put(roomNo,roomStatus);
            roomStatusList.add(statusMap);
        }


    }

}
