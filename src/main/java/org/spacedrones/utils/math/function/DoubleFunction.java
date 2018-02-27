package org.spacedrones.utils.math.function;

import org.spacedrones.utils.math.io.gui.FrameView;
import org.spacedrones.utils.math.io.gui.FunctionPlot2D;
import org.spacedrones.utils.math.io.gui.FunctionPlot3D;

public abstract class DoubleFunction {

  protected int argNumber;

  public abstract double eval(double[] values);

  public void checkArgNumber(int n) {
    if (argNumber != n) {
      throw new IllegalArgumentException("Number of arguments must equals " + argNumber);
    }
  }

  public FunctionPlot2D toPanelPlot2D(double Xmin, double Xmax) {
    return new FunctionPlot2D(this,Xmin,Xmax);
  }

  public void toFramePlot2D(double Xmin, double Xmax) {
   FrameView fv = new FrameView(toPanelPlot2D(Xmin,Xmax));
  }

  public FunctionPlot3D toPanelPlot3D(double Xmin, double Xmax, double Ymin, double Ymax) {
    return new FunctionPlot3D(this,Xmin,Xmax,Ymin,Ymax);
  }

  public void toFramePlot3D(double Xmin, double Xmax,double Ymin, double Ymax) {
   FrameView fv = new FrameView(toPanelPlot3D(Xmin,Xmax,Ymin,Ymax));
  }
}