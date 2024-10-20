package sample.tddstartproject;

public class InvalidOperationException extends RuntimeException {

  public InvalidOperationException() {
    super("Invalid operation, you need to choose one of the following operations: ");
  }
}
