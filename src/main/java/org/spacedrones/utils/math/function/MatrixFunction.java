package org.spacedrones.utils.math.function;

import org.spacedrones.utils.math.data.Matrix;

public abstract class MatrixFunction {

  int argNumber;

  public abstract Matrix eval(Matrix[] values);

  public void checkArgNumber(int n) {
    if (argNumber != n) {
      throw new IllegalArgumentException("Number of arguments must equals " + n);
    }
  }
}