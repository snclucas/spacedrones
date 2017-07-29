package org.spacedrones.utils.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import org.spacedrones.Configuration;


public class Matrix {

	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	
	
	public static double norm(double[] x) {
		double norm = 0.0;
		for(Double d : x)
			norm += d*d;
		return  Math.sqrt(norm);
	}
	
	
	public static BigDecimal norm(BigDecimal[] x) {
		BigDecimal norm = new BigDecimal(0.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		for(BigDecimal d : x)
			norm = norm.add(d.multiply(d, mc), mc);
		return  BigDecimalMath.sqrt(norm);
	}
	
	
	

	// return a random m-by-n matrix with values between 0 and 1
	public static double[][] random(int m, int n) {
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = Math.random();
		return C;
	}

	// return n-by-n identity matrix I
	public static double[][] identity(int n) {
		double[][] I = new double[n][n];
		for (int i = 0; i < n; i++)
			I[i][i] = 1;
		return I;
	}

	// return x^T y
	public static double dot(double[] x, double[] y) {
		if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
		double sum = 0.0;
		for (int i = 0; i < x.length; i++)
			sum += x[i] * y[i];
		return sum;
	}

	// return C = A^T
	public static double[][] transpose(double[][] A) {
		int m = A.length;
		int n = A[0].length;
		double[][] C = new double[n][m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[j][i] = A[i][j];
		return C;
	}

	// return C = A + B
	public static double[][] add(double[][] A, double[][] B) {
		int m = A.length;
		int n = A[0].length;
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}

	// return C = A - B
	public static double[][] subtract(double[][] A, double[][] B) {
		int m = A.length;
		int n = A[0].length;
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}
	
	
	
	public static double[] subtract(double[] A, double[] B) {
		int m = A.length;
		double[] C = new double[m];
		for (int i = 0; i < m; i++)
				C[i] = A[i] - B[i];
		return C;
	}
	
	
	public static BigDecimal[] subtract(BigDecimal[] A, BigDecimal[] B) {
		int m = A.length;
		BigDecimal[] C = new BigDecimal[m];
		for (int i = 0; i < m; i++) {
			   // C[i] = C[i].setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
			   // A[i] = A[i].setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
			   // B[i] = B[i].setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
				C[i] = A[i].subtract(B[i], mc);
		}
		return C;
	}

	
	// return C = A * B
	public static double[][] multiply(double[][] A, double[][] B) {
		int mA = A.length;
		int nA = A[0].length;
		int mB = B.length;
		int nB = B[0].length;
		if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
		double[][] C = new double[mA][nB];
		for (int i = 0; i < mA; i++)
			for (int j = 0; j < nB; j++)
				for (int k = 0; k < nA; k++)
					C[i][j] += A[i][k] * B[k][j];
		return C;
	}
	
	

	// matrix-vector multiplication (y = A * x)
	public static double[] multiply(double[][] A, double[] x) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
		double[] y = new double[m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				y[i] += A[i][j] * x[j];
		return y;
	}

	public static BigDecimal[] multiply(BigDecimal[][] A, BigDecimal[] x) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
		BigDecimal[] y = new BigDecimal[m];
		Arrays.fill(y, BigDecimal.ZERO);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				y[i] = y[i].add(A[i][j].multiply(x[j], mc), mc);
		return y;
	}


	// vector-matrix multiplication (y = x^T A)
	public static double[] multiply(double[] x, double[][] A) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
		double[] y = new double[n];
		for (int j = 0; j < n; j++)
			for (int i = 0; i < m; i++)
				y[j] += A[i][j] * x[i];
		return y;
	}

	// vector-matrix multiplication (y = x^T A)
	public static BigDecimal[] multiply(BigDecimal[] x, BigDecimal[][] A) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
		BigDecimal[] y = new BigDecimal[n];
		for (int j = 0; j < n; j++)
			for (int i = 0; i < m; i++)
				y[i] = y[i].add(A[i][j].multiply(x[j], mc), mc);
		return y;
	}


}
