package softeer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Room {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    int n = sc.nextInt();
    sc.nextLine();

    Map<String, List<int[]>> bookings = new TreeMap<>();
    for (int i = 0; i < k; i++) {
      bookings.put(sc.nextLine(), new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      String[] parts = line.split(" ");
      String room = parts[0];
      int start = Integer.parseInt(parts[1]);
      int end = Integer.parseInt(parts[2]);
      bookings.get(room).add(new int[]{start, end});
    }

    int count = 0;
    for (Entry<String, List<int[]>> s : bookings.entrySet()) {
      count++;
      final List<int[]> value = s.getValue();
      value.sort(Comparator.comparingInt(v -> v[0]));

      System.out.println("Room " + s.getKey() + ":");

      List<String> result = new ArrayList<>();
      int prev = 9;
      for (int i = 0; i < value.size(); i++) {
        final int[] ints = value.get(i);
        int start = ints[0];
        int end = ints[1];
        if (prev < start) {
          result.add(String.format("%02d-%02d", prev, start));
        }
        prev = end;
      }

      if (prev < 18) {
        result.add(String.format("%02d-%02d", prev, 18));
      }

      if (result.isEmpty()) {
        System.out.println("Not available");
      } else {
        System.out.println(result.size() + " " + "available:");
        for (String string : result) {
          System.out.println(string);
        }
      }

      if (count != bookings.size()) {
        System.out.println("-----");
      }
    }
  }
}
