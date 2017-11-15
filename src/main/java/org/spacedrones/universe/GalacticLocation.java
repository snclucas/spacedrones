package org.spacedrones.universe;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;

import java.math.BigDecimal;

public class GalacticLocation extends AbstractLocation {
  public static TypeInfo type = new TypeInfo("SimpleLocation");

  enum Epoch {
    J2000,
    B1950
  }

  private static double getGalacticRA(Epoch epoch) {
    switch (epoch) {
      case J2000:
        return 192.859508;
      case B1950:
        return 192.25;
      default:
        return 192.859508;
    }
  }

  private static double getGalacticDec(Epoch epoch) {
    switch (epoch) {
      case J2000:
        return 27.128336;
      case B1950:
        return 27.4;
      default:
        return 27.128336;
    }
  }

  private static double getGalacticPoleAngle(Epoch epoch) {
    switch (epoch) {
      case J2000:
        return 122.932 - 90.0;
      case B1950:
        return 123.0 - 90.0;
      default:
        return 122.932 - 90.0;
    }
  }

  //# Basic definitions from the introduction to the Hipparcos catalog
//# ================================================================
//# Directions to the Galactoc north pole
  double RA_ngp = 192.85948; // degree
  double DE_ngp = +27.12825; // degree
  //# Origin of the Galactic longitude
  double l_Omega = 32.93192; // degree

  //# Transformation matrix between equatorial and galactic coordinates:
//[x_G  y_G  z_G] = [x  y  z] . A_G_2000
  private static double[][] A_G_2000 = new double[][]
          {
                  {-0.0548755604, +0.4941094279, -0.8676661490},
                  {-0.8734370902, -0.4448296300, -0.1980763734},
                  {-0.4838350155, +0.7469822445, +0.4559837762}
          };

  private static double[][] A_G_2000_2 = new double[][]
          {
                  {-0.0548755604, -0.8734370902, -0.4838350155},
                  {+0.4941094279, -0.4448296300, +0.7469822445},
                  {-0.8676661490, +0.1980763734, +0.4559837762}
          };

  private static double[][] iA_G_2000 = new double[][]
          {
                  {-0.05487556047017891, -0.8734370902765137, -0.4838350155809712},
                  {0.49410942787365664, -0.4448296299177813, 0.7469822444808967},
                  {-0.8676661490228148, -0.19807637343769918, 0.4559837761897775}
          };



  private static double[] convert(double a, double b, double[][] rotMatrix, Unit unit) {
    a = a*unit.value();
    b = b*unit.value();

    double sb = Math.sin(b);
    double cb = Math.cos(b);
    double sa = Math.sin(a);
    double ca = Math.cos(a);

    double aux0 = rotMatrix[0][0] * cb * ca + rotMatrix[1][0] * cb * sa + rotMatrix[2][0] * sb;
    double aux1 = rotMatrix[0][1] * cb * ca + rotMatrix[1][1] * cb * sa + rotMatrix[2][1] * sb;
    double aux2 = rotMatrix[0][2] * cb * ca + rotMatrix[1][2] * cb * sa + rotMatrix[2][2] * sb;

    double r2 = Math.asin(aux2) * 180. / Math.PI;
    double r1 = Math.atan2(aux1, aux0) * 180. / Math.PI;


    return new double[]{r1, r2};
  }

  public static double[] convertGalacticToEquatorial(double l, double b, Unit unit) {
    return convert(l, b, iA_G_2000, unit);
  }



  public static double[] convertEquatorialToGalactic(double ra, double de, Unit unit) {
    return convert(ra, de, A_G_2000, unit);
  }

  public static double[] convertHMSEquatorialToGalactic(int ra_h, int ra_m, double ra_s, double de, Unit unit) {
    return convertDMSEquatorialToGalactic(ra_h*15.0, ra_m, ra_s, de, unit);
  }

  public static double[] convertHoursEquatorialToGalactic(double ra_h, double de, Unit unit) {
    return convertEquatorialToGalactic(ra_h*15.0, de, unit);
  }

  public static double[] convertDMSEquatorialToGalactic(double ra_d, double ra_m, double ra_s, double de, Unit unit) {
    double ra = ra_d + ra_m / 60.0 + ra_s / 3600.0;
    return convert(ra, de, A_G_2000, unit);
  }

  public static double[] convertEquatorialToCartesian(double ra, double de, Unit angleUnit, double distance, Unit distanceUnit) {
    ra = ra*angleUnit.value();
    de = de*angleUnit.value();
    double X = distance*Math.cos(de)*Math.cos(ra);
    double Y = distance*Math.cos(de)*Math.sin(ra);
    double Z = distance*Math.sin(de);
    return new double[]{X, Y, Z};
  }

  public static double[] convertHMSEquatorialToCartesian(double ra_h, double ra_m, double ra_s, double de, Unit angleUnit, double distance, Unit distanceUnit) {
    return convertDMSEquatorialToCartesian(ra_h*15.0, ra_m, ra_s, de, angleUnit, distance, distanceUnit);
  }

  public static double[] convertHoursEquatorialToCartesian(double ra_h, double de, Unit angleUnit, double distance, Unit distanceUnit) {
    return convertEquatorialToCartesian(ra_h*15.0, de, angleUnit, distance, distanceUnit);
  }

  public static double[] convertDMSEquatorialToCartesian(double ra_d, double ra_m, double ra_s, double de, Unit angleUnit, double distance, Unit distanceUnit) {
    double ra = ra_d + ra_m / 60.0 + ra_s / 3600.0;
    double X = distance*distanceUnit.value()*Math.cos(de)*Math.cos(ra);
    double Y = distance*distanceUnit.value()*Math.cos(de)*Math.sin(ra);
    double Z = distance*distanceUnit.value()*Math.sin(de);
    return new double[]{X, Y, Z};
  }

  public static double[] convertGalacticToCartesian(double l, double b, Unit angleUnit, double distance, Unit distanceUnit) {
    l = l*angleUnit.value();
    b = b*angleUnit.value();
    double[] equatorialCoords = convertGalacticToEquatorial(l, b, angleUnit);
    double ra = equatorialCoords[0];
    double de = equatorialCoords[1];
    double X = distance*distanceUnit.value()*Math.cos(de)*Math.cos(ra);
    double Y = distance*distanceUnit.value()*Math.cos(de)*Math.sin(ra);
    double Z = distance*distanceUnit.value()*Math.sin(de);
    return new double[]{X, Y, Z};
  }


  GalacticLocation(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  GalacticLocation(String name, Coordinates coordinates, Location relativeTo) {
    super(name, coordinates, relativeTo);
  }

  GalacticLocation(String name, BigDecimal... coordComponents) {
    super(name, coordComponents);
  }

  public static GalacticLocation fromGalacticCoordinates(String name, double l, double b, double distance) {
    double[] galCartesianCoords = convertGalacticToCartesian(l, b, Unit.degrees, distance, Unit.Ly);
    return new GalacticLocation(name, new Coordinates(galCartesianCoords));
  }

  public static GalacticLocation fromEquatorialCoordinates(String name, double ra, double de, double distance) {
    double[] galCartesianCoords = convertEquatorialToCartesian(ra, de, Unit.degrees, distance, Unit.Ly);
    return new GalacticLocation(name, new Coordinates(galCartesianCoords));
  }

  @Override
  public String toString() {
    return getName() + ": [" + getCoordinates().toString() + "]";
  }

  public String describe() {
    return "SimpleLocation";
  }


  public static void main(String[] args) {
    double[] result;

    //l=120.57, b=-51.508 -> alpha=11.360173, delta=11.336656

    result = convertGalacticToEquatorial(120.57, -51.508, Unit.degrees);
    System.out.println(result[0] + " " + result[1]);

    result = convertEquatorialToGalactic(result[0], result[1], Unit.degrees);
    System.out.println(result[0] + " " + result[1]);


//    \alpha = 14.4966 hours
//    \delta = -62.681 degrees
//     d = 1.29 parsecs

//x = -0.472 parsecs
//y = -0.361 parsecs
//z= -1.151 parsecs
    result = convertHoursEquatorialToCartesian(14.4966, -62.681, Unit.degrees, 1.29, Unit.Pc );
    System.out.println(result[0] + " " + result[1] + " " + result[2]);
  }

}