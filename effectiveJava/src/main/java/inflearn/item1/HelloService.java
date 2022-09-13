package inflearn.item1;

public interface HelloService {

  String hello();

  static HelloService of(String lang){
    if(lang.equals("ko")){
      return new KoreanHelloService();
    }else{
      return new EnglishHelloService();
    }
  }

  public static void main(String[] args) {
    final HelloService ko = HelloService.of("ko");
    System.out.println(ko.hello());
  }

}
