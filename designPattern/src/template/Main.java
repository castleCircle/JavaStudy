package template;

//template 메소드는 상속을 통해 슈퍼클래스의 기능을 확장할 때 사용하는 가장 대표적인 방법
//변하지 않는 기능은 슈퍼클래스에 만들어두고 자주 변경되며 확장할 기능은 서브클래스에서 만들도록 한다.
public class Main {

  public static void main(String[] args) {
    TeeMo t = new TeeMo();
    t.skillQ();
    t.skillR();

    Ganple g = new Ganple();
    g.skillQ();
    g.skillR();
  }

}
