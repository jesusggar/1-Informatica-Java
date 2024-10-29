package org.iis.ajnebro;
import static java.lang.Math.sqrt;

/**
 * Represents a simple calculator that can perform basic arithmetic operations such as addition,
 * subtraction, multiplication, and division.
 */
public class Calculator {

  /**
   * Adds two numbers together.
   *
   * @param number1 The first number to add.
   * @param number2 The second number to add.
   * @return The sum of number1 and number2.
   */
  public double add(double number1, double number2) {
    return number1 + number2;
  }

  /**
   * Subtracts one number from another.
   *
   * @param number1 The number to be subtracted from.
   * @param number2 The number to subtract.
   * @return The difference between number1 and number2.
   */
  public double subtract(double number1, double number2) {
    return number1 - number2;
  }

  /**
   * Multiplies two numbers.
   *
   * @param number1 The first number to multiply.
   * @param number2 The second number to multiply.
   * @return The product of number1 and number2.
   */
  public double multiply(double number1, double number2) {
    return number1 * number2;
  }

  /**
   * Divides one number by another.
   *
   * @param number1 The dividend.
   * @param number2 The divisor. Must not be zero.
   * @return The quotient of number1 divided by number2.
   * @throws IllegalArgumentException if number2 is zero.
   */
  public double divide(double number1, double number2) {
<<<<<<< HEAD
    boolean errorCondition = (number2 == 0);
    if (errorCondition) {
=======
    double result ;
    if (number2 == 0) {
>>>>>>> main
      throw new IllegalArgumentException("Cannot divide by zero");
    }
    result = number1/number2 ;
    return result;
  }

  public double squareRoot(double number) {
    return sqrt(number) ;
  }
}
