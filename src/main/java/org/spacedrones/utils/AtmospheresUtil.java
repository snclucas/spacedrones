package org.spacedrones.utils;


public class AtmospheresUtil {

  enum Planet {
    EARTH,
    MARS
  }

  public static void main(String[] args) {
    System.out.println(computeEarthAtmos(0.0));
  }

  static double getGeopotential(double altitude_km)
  {
    double EARTH_RADIUS =  6356.766; // km
    return EARTH_RADIUS * altitude_km / (EARTH_RADIUS + altitude_km);
  }

  static double computeEarthAtmos(double altitude /* meters */)   // Returns result in Pascals
  {
    // Below 51 km: Practical Meteorology by Roland Stull, pg 12
    // Above 51 km: http://www.braeunig.us/space/atmmodel.htm
    // Validation data: https://www.avs.org/AVS/files/c7/c7edaedb-95b2-438f-adfb-36de54f87b9e.pdf

    altitude = altitude / 1000.0;  // Convert m to km
    double geopot_height = getGeopotential(altitude);

    double t = getStandardTemperature(geopot_height);

    if (geopot_height <= 11)
      return  101325 * Math.pow(288.15 / t, -5.255877);
    else if (geopot_height <= 20)
      return 22632.06 * Math.exp(-0.1577 * (geopot_height - 11));
    else if (geopot_height <= 32)
      return 5474.889f * Math.pow(216.65 / t, 34.16319);
    else if (geopot_height <= 47)
      return 868.0187f * Math.pow(228.65 / t, 12.2011);
    else if (geopot_height <= 51)
      return 110.9063f * Math.exp(-0.1262 * (geopot_height - 47));
    else if (geopot_height <= 71)
      return 66.93887f * Math.pow(270.65 / t, -12.2011);
    else if (geopot_height <= 84.85)
      return 3.956420f * Math.pow(214.65 / t, -17.0816);

    throw new IllegalArgumentException("altitude must be less than 86 km.");
  }

  // geopot_height = earth_radius * altitude / (earth_radius + altitude) /// All in km
  // Temperature is in kelvins = 273.15 + Celsius
  static double getStandardTemperature(double geopot_height)
  {
    // Standard atmospheric pressure
    // Below 51 km: Practical Meteorology by Roland Stull, pg 12
    // Above 51 km: http://www.braeunig.us/space/atmmodel.htm

    if (geopot_height <= 11)          // Troposphere
      return 288.15f - (6.5 * geopot_height);
    else if (geopot_height <= 20)     // Stratosphere starts
      return 216.65f;
    else if (geopot_height <= 32)
      return 196.65f + geopot_height;
    else if (geopot_height <= 47)
      return 228.65f + 2.8 * (geopot_height - 32);
    else if (geopot_height <= 51)     // Mesosphere starts
      return 270.65f;
    else if (geopot_height <= 71)
      return 270.65f - 2.8 * (geopot_height - 51);
    else if (geopot_height <= 84.85)
      return 214.65f - 2 * (geopot_height - 71);
    // Thermosphere has high kinetic temperature (500 C to 2000 C) but temperature
    // as measured by a thermometer would be very low because of almost vacuum.

    throw new IllegalArgumentException("geopot_height must be less than 84.85 km.");
  }

  public static double[] computeEarthAtmos2(double altitude) {
    double temperature = 0.0, pressure = 0.0;
    if (altitude <= 36152.) {           // Troposphere
      temperature = 518.6 - 3.56 * altitude /1000. ;
      pressure = 2116. * Math.pow(temperature /518.6,5.256) ;
    }
    if (altitude >= 36152. && altitude <= 82345.) {   // Stratosphere
      temperature = 389.98 ;
      pressure = 2116. * .2236 *
              Math.exp((36000.- altitude)/(53.35*389.98)) ;
    }
    if (altitude >= 82345.) {
      temperature = 389.98 + 1.645 * (altitude -82345)/1000. ;
      pressure = 2116. *.02456 * Math.pow(temperature /389.98,-11.388) ;
    }
    return new double[]{temperature, pressure};
  }

  public static double computeAtmos(Planet planet, double altitude) {
    double temperature = 0.0;
    double pressure = 0.0;

    if (planet == Planet.EARTH) {    // Earth  standard day
      double rgas = 1718. ;                /* ft2/sec2 R */
      double gama = 1.4 ;

      if (altitude <= 36152.) {           // Troposphere
        temperature = 518.6 - 3.56 * altitude/1000. ;
      }
      if (altitude >= 36152. && altitude <= 82345.) {   // Stratosphere
        temperature = 389.98 ;
      }
      if (altitude >= 82345. && altitude <= 155348.) {
        temperature = 389.98 + 1.645 * (altitude-82345.)/1000. ;
      }
      if (altitude >= 155348. && altitude <= 175346.) {
        temperature = 508.788 ;
      }
      if (altitude >= 175346. && altitude <= 262448.) {
        temperature = 508.788 - 2.46888 * (altitude-175346.)/1000. ;
      }
    }

    if (planet == Planet.MARS) {   // Mars - curve fit of orbiter data
      double rgas = 1149. ;                /* ft2/sec2 R */
      double gamma = 1.29 ;

      if (altitude <= 22960.) {
        temperature = 434.02 - .548 * altitude/1000. ;
      }
      if (altitude > 22960.) {
        temperature = 449.36 - 1.217 * altitude/1000. ;
      }
    }
    return 6;
  }



}
