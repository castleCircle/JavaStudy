package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Room{
  int startTime;
  int endTime;

  public Room(String startTime, String endTime) {
    this.startTime = getMinutes(startTime);
    this.endTime = getMinutes(endTime) + 10;
  }

  private int getMinutes(String time){
    final String[] split = time.split(":");
    int hour = Integer.parseInt(split[0]);
    int minute = Integer.parseInt(split[1]);

    return hour * 60 + minute;
  }

}

public class 호텔대실 {

  public static void main(String[] args) {
    System.out.println(solution(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}}));
  }

  public static int solution(String[][] book_time) {

    PriorityQueue<Room> useRooms = new PriorityQueue<>(Comparator.comparing(o->o.endTime));

    Room[] rooms = new Room[book_time.length];
    for(int i=0;i<book_time.length;i++){
      rooms[i] = new Room(book_time[i][0],book_time[i][1]);
    }

    Arrays.sort(rooms,Comparator.comparing(o->o.startTime));

    int answer = 0;

    for(Room room : rooms){
      if(!useRooms.isEmpty() && useRooms.peek().endTime <= room.startTime){
        useRooms.poll();
      }else{
        answer++;
      }

      useRooms.offer(room);
    }

    return answer;
  }

}
