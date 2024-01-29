package concept;

public class PowerSet {

  private static char[] inputs = {'a','b','c'};
  private static boolean[] checks;

  public static void main(String[] args) {
    checks = new boolean[inputs.length];
    powerSet(0);
  }

  public static void powerSet(int n){
    if(n==inputs.length){
      for(int i=0;i<inputs.length;i++){
        if(checks[i]){
          System.out.print(inputs[i]);
        }
      }
      System.out.println();
      return;
    }

    checks[n] = false;
    powerSet(n+1);
    checks[n] = true;
    powerSet(n+1);


  }


}
