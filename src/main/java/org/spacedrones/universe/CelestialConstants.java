package org.spacedrones.universe;

import org.spacedrones.physics.Unit;

public class CelestialConstants {
	public static double O_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double B_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double A_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double F_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double G_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double K_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double M_STAR_RADIUS =  695500 * Unit.Km.value();
	
	public static double G_STAR_LUMINOSITY = 3.846E26 * Unit.W.value();
	
	//SOLAR CONSTANTS
  public static double Lsun = 3.839e33; //erg s-1
  public static double Rsun = 6.955e10; //cm

  //PLANETARY CONSTANTS
  public static double massMerc    = 3.303e26*Unit.kg.value();
  public static double massVenus   = 4.870e27*Unit.kg.value();;
  public static double massEarth   = 5.976e27*Unit.kg.value();;
  public static double massMars    = 6.418e26*Unit.kg.value();
  public static double massJup     = 1.899e30*Unit.kg.value();;
  public static double massSaturn  = 5.686e29*Unit.kg.value();;
  public static double massUranus  = 8.66e28*Unit.kg.value();;
  public static double massNeptune = 1.030e29*Unit.kg.value();;
  public static double massPluto   = 1.0e25*Unit.kg.value();;

  public static double radMerc       = 2.439e8*Unit.Km.value();
  public static double radVenus      = 6.050e8*Unit.Km.value();
  public static double radEarth      = 6.378e8*Unit.Km.value();
  public static double radMars       = 3.397e8*Unit.Km.value();
  public static double radJup        = 7.140e9*Unit.Km.value();
  public static double radSaturn     = 6.0e9*Unit.Km.value();
  public static double radUranus     = 2.615e9*Unit.Km.value();
  public static double radNeptune    = 2.43e9*Unit.Km.value();
  public static double radPluto      = 1.2e8*Unit.Km.value();

  //YEARS
  public static double periodMerc    = 2.4085e-1*Unit.year.value();
  public static double periodVenus   = 6.1521e-1*Unit.year.value();
  public static double periodEarth   = 1.0004*Unit.year.value();
  public static double periodMars    = 1.88089*Unit.year.value();
  public static double periodJup     = 1.18622e1*Unit.year.value();
  public static double periodSaturn  = 2.94577e1*Unit.year.value();
  public static double periodUranus  = 8.40139e1*Unit.year.value();
  public static double periodNeptune = 1.64793e2*Unit.year.value();
  public static double periodPluto   = 2.47686e2*Unit.year.value();

  //AU
  public static double smaMerc    = 3.87096e-1*Unit.AU.value();
  public static double smaVenus   = 7.23342e-1*Unit.AU.value();
  public static double smaEarth   = 9.99987e-1*Unit.AU.value();
  public static double smaMars    = 1.523705*Unit.AU.value();
  public static double smaJup     = 5.204529*Unit.AU.value();
  public static double smaSaturn  = 9.575133*Unit.AU.value();
  public static double smaUranus  = 1.930375e1*Unit.AU.value();
  public static double smaNeptune = 3.020652e1*Unit.AU.value();
  public static double smaPluto   = 3.991136e1*Unit.AU.value();

  public static double eccMerc    = 0.205622;
  public static double eccVenus   = 0.006783;
  public static double eccEarth   = 0.016684;
  public static double eccMars    = 0.093404;
  public static double eccJup     = 0.047826;
  public static double eccSaturn  = 0.052754;
  public static double eccUranus  = 0.050363;
  public static double eccNeptune = 0.004014;
  public static double eccPluto   = 0.256695;

}
