package org.spacedrones.utils.math.function;


import org.spacedrones.utils.math.data.Matrix;
import org.spacedrones.utils.math.function.expressionParser.Evaluator;

public class TestDoubleFunctionExpression extends TestDoubleFunction {

  private String expression;
  private String[] variables;
  private Evaluator E = new Evaluator();

  public TestDoubleFunctionExpression(String exp,String[] vars) {
    argNumber = vars.length;
    setFunction(exp,vars);
  }

  public TestDoubleFunctionExpression(String exp,String vars) {
    argNumber = 1;
    String[] variable = new String[1];
    variable[0] = vars;
    setFunction(exp,variable);
  }

  private void setFunction(String exp,String[] vars) {
    expression = exp;
    variables = vars;
  }


  private void setVariableValues(double[] values) {
    for (int i = 0;i<variables.length;i++) {
      E.addVariable(variables[i],values[i]);
    }
    E.setExpression(expression);
  }

  private void setVariableValues(double value) {
    E.addVariable(variables[0],value);
    E.setExpression(expression);
  }

  private void setVariableValues(Matrix values) {
    for (int i = 0;i<variables.length;i++) {
      E.addVariable(variables[i],values.get(i,0));
    }
    E.setExpression(expression);
  }

  public boolean eval(double[] values) {
    checkArgNumber(values.length);
    setVariableValues(values);
    return (Double) (E.getValue()) > 0.5;
  }

  public boolean eval(double value) {
    checkArgNumber(1);
    setVariableValues(value);
    return (Double) (E.getValue()) > 0.5;
  }

  public boolean eval(Matrix values) {
    checkArgNumber(values.getRowDimension());
    setVariableValues(values);
    return (Double) (E.getValue()) > 0.5;
  }
}
