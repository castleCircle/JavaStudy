package ohouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Frequency implements Comparable<Frequency>{

  char alpha;
  int cnt;

  public Frequency(char alpha,int cnt){
    this.alpha = alpha;
    this.cnt = cnt;
  }

  public void increment(){this.cnt++;}

  @Override
  public int compareTo(Frequency o) {

    if(o.cnt == this.cnt){
      return this.alpha - o.alpha;
    }

    return o.cnt-this.cnt;
  }
}

public class Main2 {

  public static void main(String[] args) {
    System.out.println(solution("bucketplace"));

  }

  public static String solution(String s){

    final char[] chars = s.toCharArray();

    Map<Character,Frequency> map = new HashMap<>();
    List<Frequency> list = new ArrayList<>();

    for(char input : chars){
      if(!map.containsKey(input)){
        Frequency frequency = new Frequency(input,1);
        map.put(input,frequency);
        list.add(frequency);
      }else{
        map.get(input).increment();
      }
    }

    Collections.sort(list);

    StringBuilder sb = new StringBuilder();
    for(int i=0;i<list.size();i++){
      final Frequency frequency = list.get(i);
      int cnt = frequency.cnt;
      while(cnt-- > 0){
        System.out.print(frequency.alpha);
        sb.append(frequency.alpha);
      }

    }
    return sb.toString();
  }

}
