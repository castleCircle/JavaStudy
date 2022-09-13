package inflearn.item1.test;

public interface TestAlpa {

  String indexNumber();

  static  TestAlpa of(String type){
    if(type.equals("A")){
      return new TestA();
    }else{
      return new TestB();
    }
  }

  static void main(String[] args) {
    //특정 인스턴스에 종속되지 않음
    final TestAlpa alpa = TestAlpa.of("A");
    System.out.println(alpa.indexNumber());
  }

}
