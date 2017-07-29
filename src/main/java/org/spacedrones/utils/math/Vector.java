package org.spacedrones.utils.math;


/**
 * Vector stores and manipulates xyz triples representing 3 space
 * vectors.
 *
 * @author Anthony Steed
 * @version 1.0
 */

public class Vector {

	public double X;
	public double Y;
	public double Z;

	/** 
	 * Create a new vector and set it to zero
	 */
	public Vector() {
		X=0.0;Y=0.0;Z=0.0;
	}

	/** 
	 * Create a new vector with the given x,y and z values
	 * @param x the x value
	 * @param y the y value
	 * @param z the z value
	 */
	public Vector(double x, double y, double z) {
		X=x; Y=y; Z=z;
	}

	/** 
	 * Set this vector to the given x,y and z values
	 * @param x the x value
	 * @param y the y value
	 * @param z the z value
	 */
	public void set(double x, double y, double z) {
		X=x; Y=y; Z=z;
	}

	/** 
	 * Create a new vector by copying an existing vector
	 * @param v the vector to copy
	 */
	public Vector(Vector v) {
		X=v.X;
		Y=v.Y;
		Z=v.Z;
	}

	/** 
	 * Copy an existing vector to this vector
	 * @param v the vector to copy
	 */
	public void copy(Vector v) {
		X=v.X;
		Y=v.Y;
		Z=v.Z;
	}


	/** 
	 * Find the squared length of this vector
	 * @return the squared length
	 */
	public double squarednorm() {
		return  X*X+Y*Y+Z*Z;
	}

	/** 
	 * Find the length of this vector
	 * @return the length
	 */
	public double norm() {
		return  Math.sqrt(X*X+Y*Y+Z*Z);
	}

	/** 
	 * Normalise this vector
	 */
	public void normalise() {
		double d = norm();

		X /= d;
		Y /= d;
		Z /= d;
	}

	/** 
	 * Create a new vector as the normalisation of this vector
	 * @return the new vector with the normalised version
	 */
	public Vector normalised() {
		Vector v = new Vector(this);
		v.normalise();
		return v;
	}


	/** 
	 * Subtract the given vector from this vector
	 * @param v the vector to subtract
	 */
	public void subtract(Vector v) {
		X = X - v.X;
		Y = Y - v.Y;
		Z = Z - v.Z;
	}

	/** 
	 * Static function to create a new vector as the difference between
	 * two vectors.
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the new difference vector
	 */
	public static Vector subtract(Vector v1, Vector v2) {
		return new Vector (v1.X - v2.X, v1.Y - v2.Y, v1.Z - v2.Z);
	}

	/** 
	 * Static function to find the difference between two vectors and write
	 * it to a given destination vector.
	 * @param vd the destination vector
	 * @param v1 the first vector
	 * @param v2 the second vector
	 */
	public static void subtract(Vector vd, Vector v1, Vector v2) {
		vd.X = v1.X - v2.X;
		vd.Y = v1.Y - v2.Y;
		vd.Z = v1.Z - v2.Z;
	}

	/** 
	 * Add the given vector to this vector
	 * @param v the vector to add
	 */
	public void add(Vector v) {
		X += v.X;
		Y += v.Y; 
		Z += v.Z;
	}

	/** 
	 * Static function to sum two vector and write it to a given
	 * destination vector.
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the new vector
	 */
	public static Vector add(Vector v1, Vector v2) {
		return new Vector (v1.X + v2.X, v1.Y + v2.Y, v1.Z + v2.Z);
	}

	/** 
	 * Static function to sum two vectors and write it to a given
	 * destination vector.
	 * @param vd the destination vector
	 * @param v1 the first vector
	 * @param v2 the second vector
	 */
	public static void add(Vector vd, Vector v1, Vector v2) {
		vd.X = v1.X + v2.X;
		vd.Y = v1.Y + v2.Y;
		vd.Z = v1.Z + v2.Z;
	}

	/** 
	 * Cross product the given vector with this vector
	 * @param v the vector to cross with
	 */
	public void cross (Vector v) {
		double x,y,z;

		x = (Y*v.Z) - (Z*v.Y);
		y = (Z*v.X) - (X*v.Z);
		z = (X*v.Y) - (Y*v.X);
		X=x;
		Y=y;
		Z=z;
	}

	/** 
	 * Static cross product methods that creates a new vector with the
	 * cross product of the two given vectors.
	 * @param v1 the first vector to cross with
	 * @param v2 the second vector to cross with
	 * @return a new vector containing the cross product
	 */
	public static Vector cross(Vector v1, Vector v2) {
		return new Vector ((v1.Y*v2.Z) - (v1.Z*v2.Y),
				(v1.Z*v2.X) - (v1.X*v2.Z),
				(v1.X*v2.Y) - (v1.Y*v2.X));
	}

	/** 
	 * Static cross product methods that write the cross product of the
	 * two given vectors to the given desination vector.
	 * @param vd the vector to store the cross product in
	 * @param v1 the first vector to cross with
	 * @param v2 the second vector to cross with
	 */
	public static void cross(Vector vd, Vector v1, Vector v2) {
		vd.X = (v1.Y*v2.Z) - (v1.Z*v2.Y);
		vd.Y = (v1.Z*v2.X) -  (v1.X*v2.Z);
		vd.Z = (v1.X*v2.Y) - (v1.Y*v2.X);
	}

	/** 
	 * Dot product the given vector with this vector
	 * @param v the vector to dot product with
	 * @return the dot product
	 */
	public double dot(Vector v) {
		return (X * v.X) + (Y * v.Y) + (Z * v.Z);
	}


	/**  
	 * Static method that returns the dot product of the two given *
	 * vectors.
	 * @param v1 the first vector of the dot product
	 * @param v2 the second vector of the dot product
	 * @return the dot product
	 */
	public static double dot(Vector v1, Vector v2) {
		return (v1.X * v2.X) + (v1.Y * v2.Y) + (v1.Z * v2.Z);
	}

	/** 
	 * Scale this vector by the given factor
	 * @param s the scale factor
	 */
	public void scale(double s) {
		X *=s;
		Y *=s;
		Z *=s;
	}

	/** 
	 * Static scale method that creates a new vector from a given
	 * vector and scale factor.
	 * @param v the vector to scale
	 * @param s the scale factor
	 * @return a scaled vector
	 */
	public static Vector scale(Vector v, double s) {
		return new Vector (v.X*s, v.Y*s, v.Z*s);
	}

	/** 
	 * Static scale method that writes a scaled vector based upon the
	 * given vector and scale factor.
	 * @param vd the destination vector
	 * @param v the vector to scale
	 * @param s the scale factor
	 */
	public static void scale(Vector vd, Vector v, double s) {
		vd.X = v.X*s;
		vd.Y = v.Y*s;
		vd.Z = v.Z*s;
	}

	/** 
	 * Negate this vector
	 */
	public void negate() {
		X = -X;
		Y = -Y;
		Z = -Z;
	}

	/** 
	 * Static negate method that creates a new vector from a given
	 * vector.
	 * @param v the vector to negate
	 * @return a negated vector
	 */
	public static Vector negate(Vector v) {
		return new Vector (-v.X,-v.Y,-v.Z);
	}


	public String toString()  {
		return "[" + X + ", " + Y + ", " + Z + "]";
	}
}