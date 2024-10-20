package sample.tddstartproject;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  @Test
  public void 덧셈을_할_수있다(){
    long num1 = 2;
    long num2 = 3;
    String operator = "+";
    Calculator calculator = new Calculator();

    long result = calculator.calculate(num1,operator,num2);

    Assertions.assertThat(result).isEqualTo(5);


  }

  @Test
  public void 뺄셈을_할_수있다(){
    long num1 = 3;
    long num2 = 2;
    String operator = "-";
    Calculator calculator = new Calculator();

    long result = calculator.calculate(num1,operator,num2);

    Assertions.assertThat(result).isEqualTo(1);


  }


  @Test
  public void 곱셈을_할_수있다(){
    long num1 = 2;
    long num2 = 3;
    String operator = "*";
    Calculator calculator = new Calculator();

    long result = calculator.calculate(num1,operator,num2);

    Assertions.assertThat(result).isEqualTo(6);


  }

  @Test
  public void 나누기을_할_수있다(){
    long num1 = 4;
    long num2 = 2;
    String operator = "/";
    Calculator calculator = new Calculator();

    long result = calculator.calculate(num1,operator,num2);

    Assertions.assertThat(result).isEqualTo(2);


  }

  @Test
  public void 잘못된연산을_할_수있다(){
    long num1 = 4;
    long num2 = 2;
    String operator = "2";
    Calculator calculator = new Calculator();

    long result = calculator.calculate(num1,operator,num2);

    Assertions.assertThatThrownBy(()->calculator.calculate(num1,operator,num2))
        .isInstanceOf(InvalidOperationException.class);


  }

}