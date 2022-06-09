package item1;

public class Book {

  private String name;
  private String author;

  public Book(String name){
    this.name = name;
  }

  public static Book createBookWithName(String name){
    return new Book(name);
  }

}
