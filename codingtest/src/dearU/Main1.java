package dearU;

public class Main1 {

  public static void main(String[] args) {
    System.out.println(solution("PM 01:00:00",10));
    System.out.println(solution("PM 11:59:59",1));
  }

  public static String solution(String p,int n){
    // 현재 시간을 오전/오후 여부와 시간, 분, 초로 분리
    String[] time = p.split(" "); // P를 공백을 기준으로 시간과 오전/오후 부분으로 나누기
    String[] hourMinSec = time[1].split(":"); // 시간 부분을 다시 : 을 기준으로 나누기

    int hour = Integer.parseInt(hourMinSec[0]); // 시간 부분 가져오기
    int minute = Integer.parseInt(hourMinSec[1]); // 분 부분 가져오기
    int second = Integer.parseInt(hourMinSec[2]); // 초 부분 가져오기

    if (time[0].equals("PM") && hour != 12) { // 오후이면서 12시가 아닌 경우, 시간에 12 더해주기
      hour += 12;
    } else if (time[0].equals("AM") && hour == 12) { // 오전이면서 12시인 경우, 시간에 12 빼주기
      hour -= 12;
    }

    // N초를 더해주기
    second += n;
    if (second >= 60) { // 초가 60초 이상인 경우, 분에 1 더해주고 초는 0으로 만들기
      minute += second / 60;
      second %= 60;
    }
    if (minute >= 60) { // 분이 60분 이상인 경우, 시간에 1 더해주고 분은 0으로 만들기
      hour += minute / 60;
      minute %= 60;
    }
    if (hour >= 24) { // 시간이 24시간 이상인 경우, 24로 나눈 나머지 값으로 변경하기
      hour %= 24;
    }

    // 시간, 분, 초를 두 자리 수로 변환하여 문자열로 만들어주기
    String hourStr = String.format("%02d", hour);
    String minuteStr = String.format("%02d", minute);
    String secondStr = String.format("%02d", second);

    return hourStr + ":" + minuteStr + ":" + secondStr;
  }



}
