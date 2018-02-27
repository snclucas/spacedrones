package org.spacedrones.utils.math.function.expressionParser;


public class Driver {

  public Driver() {
    Evaluator eval = new Evaluator("5/cos(5)*3");
    System.out.println(eval.getValue());
  }

  public static void main(String[] args) {
    new Driver();
  }
}
