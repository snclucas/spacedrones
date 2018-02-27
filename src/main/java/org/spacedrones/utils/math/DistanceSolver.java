package org.spacedrones.utils.math;

import org.spacedrones.Configuration;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DistanceSolver {

	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);

	public static double[] solve(double precision, double x1, double y1, double z1, double d1, double x2, double y2, double z2, double d2, double x3, double y3, double z3, double d3) {
		double[] rn  = new double[]{1.0, 1.0, 1.0};

		for(;;) {
			double[] func = function(rn, x1,  y1,  z1,  x2,  y2,  z2,  x3,  y3,  z3, d1, d2, d3);
			double[][] invJ2 = invert(jacobian(rn, x1,  y1,  z1,  x2,  y2,  z2,  x3,  y3,  z3, d1, d2, d3)) ;
			rn = Matrix2.subtract(rn, Matrix2.multiply(invJ2, func));

			if(Matrix2.norm(func) <= precision)
				break;
		}
		return rn;
	}


	public static BigDecimal[] solve(BigDecimal precision, BigDecimal x1, BigDecimal y1, BigDecimal z1, BigDecimal d1, BigDecimal x2, BigDecimal y2, BigDecimal z2, BigDecimal d2, BigDecimal x3, BigDecimal y3, BigDecimal z3, BigDecimal d3) {
		BigDecimal[] rn  = new BigDecimal[]{new BigDecimal(1.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP), new BigDecimal(1.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP), new BigDecimal(1.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP)};
		precision = precision.setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		for(;;) {
			BigDecimal[] func = function(rn, x1,  y1,  z1,  x2,  y2,  z2,  x3,  y3,  z3, d1, d2, d3);
			BigDecimal[][] invJ2 = invert(jacobian(rn, x1,  y1,  z1,  x2,  y2,  z2,  x3,  y3,  z3, d1, d2, d3)) ;
			rn = Matrix2.subtract(rn, Matrix2.multiply(invJ2, func));

			if(Matrix2.norm(func).compareTo(precision) < 0)
				break;
		}
		return rn;
	}


	private static double[] function(double[] XYZ, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double d1, double d2, double d3) {
		double f1 = Math.sqrt(Math.pow((XYZ[0]-x1),2.0) + Math.pow((XYZ[1]-y1),2.0) + Math.pow((XYZ[2]-z1),2.0))-d1;
		double f2 = Math.sqrt(Math.pow((XYZ[0]-x2),2.0) + Math.pow((XYZ[1]-y2),2.0) + Math.pow((XYZ[2]-z2),2.0))-d2;
		double f3 = Math.sqrt(Math.pow((XYZ[0]-x3),2.0) + Math.pow((XYZ[1]-y3),2.0) + Math.pow((XYZ[2]-z3),2.0))-d3;
		return new double[]{f1, f2, f3};
	}


	private static BigDecimal[] function(BigDecimal[] XYZ, BigDecimal x1, BigDecimal y1, BigDecimal z1, BigDecimal x2, BigDecimal y2, BigDecimal z2, BigDecimal x3, BigDecimal y3, BigDecimal z3, BigDecimal d1, BigDecimal d2, BigDecimal d3) {
		BigDecimal two = new BigDecimal(2.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		BigDecimal f1 = BigDecimalMath.sqrt(BigDecimalMath.pow((XYZ[0].subtract(x1, mc).abs(mc)), two) .add( BigDecimalMath.pow((XYZ[1].subtract(y1, mc).abs(mc)), two), mc) .add( BigDecimalMath.pow((XYZ[2].subtract(z1, mc).abs(mc)), two), mc)).subtract(d1, mc);
		BigDecimal f2 = BigDecimalMath.sqrt(BigDecimalMath.pow((XYZ[0].subtract(x2, mc).abs(mc)), two) .add( BigDecimalMath.pow((XYZ[1].subtract(y2, mc).abs(mc)), two), mc) .add( BigDecimalMath.pow((XYZ[2].subtract(z2, mc).abs(mc)), two), mc)).subtract(d2, mc);
		BigDecimal f3 = BigDecimalMath.sqrt(BigDecimalMath.pow((XYZ[0].subtract(x3, mc).abs(mc)), two) .add( BigDecimalMath.pow((XYZ[1].subtract(y3, mc).abs(mc)), two), mc) .add( BigDecimalMath.pow((XYZ[2].subtract(z3, mc).abs(mc)), two), mc)).subtract(d3, mc);
		return new BigDecimal[]{f1, f2, f3};
	}

	private static double[][] jacobian(double[] XYZ, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double d1, double d2, double d3) {

		double denom1 = Math.sqrt(Math.pow((XYZ[0]-x1),2.0) + Math.pow((XYZ[1]-y1),2.0) + Math.pow((XYZ[2]-z1),2.0));
		double denom2 = Math.sqrt(Math.pow((XYZ[0]-x2),2.0) + Math.pow((XYZ[1]-y2),2.0) + Math.pow((XYZ[2]-z2),2.0));
		double denom3 = Math.sqrt(Math.pow((XYZ[0]-x3),2.0) + Math.pow((XYZ[1]-y3),2.0) + Math.pow((XYZ[2]-z3),2.0));

		double df1dx = (denom1==0.0)?0.0:(XYZ[0]-x1)/denom1;
		double df1dy = (denom1==0.0)?0.0:(XYZ[1]-y1)/denom1;
		double df1dz = (denom1==0.0)?0.0:(XYZ[2]-z1)/denom1;

		double df2dx = (denom2==0.0)?0.0:(XYZ[0]-x2)/denom2;
		double df2dy = (denom2==0.0)?0.0:(XYZ[1]-y2)/denom2;
		double df2dz = (denom2==0.0)?0.0:(XYZ[2]-z2)/denom2;

		double df3dx = (denom3==0.0)?0.0:(XYZ[0]-x3)/denom3;
		double df3dy = (denom3==0.0)?0.0:(XYZ[1]-y3)/denom3;
		double df3dz = (denom3==0.0)?0.0:(XYZ[2]-z3)/denom3;

		return new double[][]{{df1dx,df1dy,df1dz},{df2dx,df2dy,df2dz},{df3dx,df3dy,df3dz}};
	}


	private static BigDecimal[][] jacobian(BigDecimal[] XYZ, BigDecimal x1, BigDecimal y1, BigDecimal z1, BigDecimal x2, BigDecimal y2, BigDecimal z2, BigDecimal x3, BigDecimal y3, BigDecimal z3, BigDecimal d1, BigDecimal d2, BigDecimal d3) {

		BigDecimal two = new BigDecimal(2.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		BigDecimal denom1 = BigDecimalMath.sqrt(BigDecimalMath.pow((XYZ[0].subtract(x1, mc).abs(mc)), two) .add( BigDecimalMath.pow((XYZ[1].subtract(y1, mc).abs(mc)), two), mc) .add( BigDecimalMath.pow((XYZ[2].subtract(z1, mc).abs(mc)), two), mc));
		BigDecimal denom2 = BigDecimalMath.sqrt(BigDecimalMath.pow((XYZ[0].subtract(x2, mc).abs(mc)), two) .add( BigDecimalMath.pow((XYZ[1].subtract(y2, mc).abs(mc)), two), mc) .add( BigDecimalMath.pow((XYZ[2].subtract(z2, mc).abs(mc)), two), mc));
		BigDecimal denom3 = BigDecimalMath.sqrt(BigDecimalMath.pow((XYZ[0].subtract(x3, mc).abs(mc)), two) .add( BigDecimalMath.pow((XYZ[1].subtract(y3, mc).abs(mc)), two), mc) .add( BigDecimalMath.pow((XYZ[2].subtract(z3, mc).abs(mc)), two), mc));

		BigDecimal df1dx = (XYZ[0].subtract(x1, mc)).divide(denom1, mc);
		BigDecimal df1dy = (XYZ[1].subtract(y1, mc)).divide(denom1, mc);
		BigDecimal df1dz = (XYZ[2].subtract(z1, mc)).divide(denom1, mc);

		BigDecimal df2dx = (XYZ[0].subtract(x2, mc)).divide(denom2, mc);
		BigDecimal df2dy = (XYZ[1].subtract(y2, mc)).divide(denom2, mc);
		BigDecimal df2dz = (XYZ[2].subtract(z2, mc)).divide(denom2, mc);

		BigDecimal df3dx = (XYZ[0].subtract(x3, mc)).divide(denom3, mc);
		BigDecimal df3dy = (XYZ[1].subtract(y3, mc)).divide(denom3, mc);
		BigDecimal df3dz = (XYZ[2].subtract(z3, mc)).divide(denom3, mc);

		return new BigDecimal[][]{{df1dx,df1dy,df1dz},{df2dx,df2dy,df2dz},{df3dx,df3dy,df3dz}};
	}



	public static BigDecimal[][] invert(BigDecimal a[][]) {
		int n = a.length;
		BigDecimal x[][] =new BigDecimal[n][n];
		BigDecimal b[][] = new BigDecimal[n][n];

		int index[] = new int[n];
		for (int i=0; i<a.length; ++i)
			for(int j =0; j<a[i].length; j++)
			{
				if(i==j)
					b[i][i] = new BigDecimal("1").setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
				else
					b[i][j] = new BigDecimal("0").setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
			}


		//int index[] = new int[n];
		for (int i=0; i<n; ++i) b[i][i] = new BigDecimal("1").setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
    // Transform the matrix into an upper triangle
		gaussian(a, index);
		// Update the matrix b[i][j] with the ratios stored
		for (int i=0; i<n-1; ++i)
			for (int j=i+1; j<n; ++j)
				for (int k=0; k<n; ++k)
					b[index[j]][k]
							=b[index[j]][k].subtract( a[index[j]][i].multiply (b[index[i]][k], mc), mc);
		// Perform backward substitutions
		for (int i=0; i<n; ++i) {
			x[n-1][i] = b[index[n-1]][i].divide(a[index[n-1]][n-1], mc);
			for (int j=n-2; j>=0; --j) {
				x[j][i] = b[index[j]][i];
				for (int k=j+1; k<n; ++k) {
					x[j][i] =x[j][i].subtract( a[index[j]][k].multiply(x[k][i], mc), mc);
				}
				x[j][i] = x[j][i].divide(a[index[j]][j], mc);
			}
		}
		return x;
	}
	// Method to carry out the partial-pivoting Gaussian
	// elimination.  Here index[] stores pivoting order.
	public static void gaussian(BigDecimal a[][],
			int index[]) {
		int n = index.length;
		BigDecimal c[] = new BigDecimal[n];
		// Initialize the index
		for (int i=0; i<n; ++i) index[i] = i;
		// Find the rescaling factors, one from each row
		for (int i=0; i<n; ++i) {
			BigDecimal c1 = new BigDecimal("0").setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
      for (int j=0; j<n; ++j) {
				BigDecimal c0 = (a[i][j]).abs(mc);
				if (c0.compareTo(c1)>0) c1 = c0;
			}
			c[i] = c1;
		}
		// Search the pivoting element from each column
		int k = 0;
		for (int j=0; j<n-1; ++j) {
			BigDecimal pi1 = new BigDecimal("0").setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
      for (int i=j; i<n; ++i) {
				BigDecimal pi0 =a[index[i]][j].abs(mc);
				pi0 =pi0.divide(c[index[i]], mc);
				if (pi0.compareTo (pi1) >0) {
					pi1 = pi0;
					k = i;
				}
			}
			// Interchange rows according to the pivoting order
			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i=j+1; i<n; ++i) {
				BigDecimal pj = a[index[i]][j].divide(a[index[j]][j], mc);
				// Record pivoting ratios below the diagonal
				a[index[i]][j] = pj;
				// Modify other elements accordingly
				for (int l=j+1; l<n; ++l)
					a[index[i]][l] =a[index[i]][l].subtract( pj.multiply(a[index[j]][l], mc), mc);
			}
		}
	}





	public static double[][] invert(final double a[][]) {
		int n = a.length;
		double x[][] = new double[n][n];
		double b[][] = new double[n][n];
		int index[] = new int[n];
		for (int i=0; i<n; ++i) b[i][i] = 1;
		// Transform the matrix into an upper triangle
		gaussian(a, index);
		// Update the matrix b[i][j] with the ratios stored
		for (int i=0; i<n-1; ++i)
			for (int j=i+1; j<n; ++j)
				for (int k=0; k<n; ++k)
					b[index[j]][k]
							-= a[index[j]][i]*b[index[i]][k];
		// Perform backward substitutions
		for (int i=0; i<n; ++i) {
			x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
			for (int j=n-2; j>=0; --j) {
				x[j][i] = b[index[j]][i];
				for (int k=j+1; k<n; ++k) {
					x[j][i] -= a[index[j]][k]*x[k][i];
				}
				x[j][i] /= a[index[j]][j];
			}
		}
		return x;
	}
	// Method to carry out the partial-pivoting Gaussian
	// elimination.  Here index[] stores pivoting order.
	public static void gaussian(double a[][],
			int index[]) {
		int n = index.length;
		double c[] = new double[n];
		// Initialize the index
		for (int i=0; i<n; ++i) index[i] = i;
		// Find the rescaling factors, one from each row
		for (int i=0; i<n; ++i) {
			double c1 = 0;
			for (int j=0; j<n; ++j) {
				double c0 = Math.abs(a[i][j]);
				if (c0 > c1) c1 = c0;
			}
			c[i] = c1;
		}
		// Search the pivoting element from each column
		int k = 0;
		for (int j=0; j<n-1; ++j) {
			double pi1 = 0;
			for (int i=j; i<n; ++i) {
				double pi0 = Math.abs(a[index[i]][j]);
				pi0 /= c[index[i]];
				if (pi0 > pi1) {
					pi1 = pi0;
					k = i;
				}
			}
			// Interchange rows according to the pivoting order
			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i=j+1; i<n; ++i) {
				double pj = a[index[i]][j]/a[index[j]][j];
				// Record pivoting ratios below the diagonal
				a[index[i]][j] = pj;
				// Modify other elements accordingly
				for (int l=j+1; l<n; ++l)
					a[index[i]][l] -= pj*a[index[j]][l];
			}
		}
	}

}
