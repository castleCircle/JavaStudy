package item1;

import lombok.ToString;

@ToString
public class Book {

  private String name;

  public Book(String name){
    this.name = name;
  }

  public static Book createBook(final String name){
    return new Book(name);
  }
}
