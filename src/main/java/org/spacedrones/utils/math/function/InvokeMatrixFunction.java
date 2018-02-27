package org.spacedrones.utils.math.function;


import org.spacedrones.utils.math.data.Matrix;
import org.spacedrones.utils.math.io.data.MatrixFile;

import java.io.File;

public class InvokeMatrixFunction {

  private File functionFile;
  private File resultFile;

  public InvokeMatrixFunction(String fn,String rf) {
      functionFile = new File(fn);
      resultFile = new File(rf);
  }

  public InvokeMatrixFunction(File fn,File rf) {
      functionFile = fn;
      resultFile = rf;
  }

  public Matrix eval() {
    try {
      Process p = Runtime.getRuntime().exec(functionFile.getName());
      p.waitFor();
      MatrixFile mf = new MatrixFile(resultFile);
      Matrix X = mf.getMatrix();
      return X;
    } catch (Exception e) {
      System.out.println("Error : File " + resultFile +" unreadable : "+e);
      return null;
    }
  }

}