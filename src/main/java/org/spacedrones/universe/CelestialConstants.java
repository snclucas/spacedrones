package org.spacedrones.universe;

import org.spacedrones.physics.Constants;
import org.spacedrones.physics.Unit;

public class CelestialConstants {
	public static double O_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double B_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double A_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double F_STAR_RADIUS =  695500 * Unit.Km.value();

	public static double G2V_STAR_RADIUS =  695500 * Unit.Km.value();


	public static double K_STAR_RADIUS =  695500 * Unit.Km.value();
	public static double M_STAR_RADIUS =  695500 * Unit.Km.value();


  public static double M0V_STAR_RADIUS = Constants.R0 * 0.62;
  public static double M1V_STAR_RADIUS = Constants.R0 * 0.49;
  public static double M2V_STAR_RADIUS = Constants.R0 * 0.44;
  public static double M3V_STAR_RADIUS = Constants.R0 * 0.39;
  public static double M4V_STAR_RADIUS = Constants.R0 * 0.26;
  public static double M5V_STAR_RADIUS = Constants.R0 * 0.20;
  public static double M6V_STAR_RADIUS = Constants.R0 * 0.15;
  public static double M7V_STAR_RADIUS = Constants.R0 * 0.12;
  public static double M8V_STAR_RADIUS = Constants.R0 * 0.11;
  public static double M9V_STAR_RADIUS = Constants.R0 * 0.08;



  public static double G0V_STAR_MASS = Constants.M0 * 1.15;
  public static double G1V_STAR_MASS = Constants.M0 * 1.10;
  public static double G2V_STAR_MASS = Constants.M0 * 1.07;
  public static double G3V_STAR_MASS = Constants.M0 * 1.04;
  public static double G4V_STAR_MASS = Constants.M0 * 1.00;
  public static double G5V_STAR_MASS = Constants.M0 * 0.98;
  public static double G6V_STAR_MASS = Constants.M0 * 0.93;
  public static double G7V_STAR_MASS = Constants.M0 * 0.90;
  public static double G8V_STAR_MASS = Constants.M0 * 0.87;
  public static double G9V_STAR_MASS = Constants.M0 * 0.84;

  public static double M0V_STAR_MASS = Constants.M0 * 0.6;
  public static double M1V_STAR_MASS = Constants.M0 * 0.49;
  public static double M2V_STAR_MASS = Constants.M0 * 0.44;
  public static double M3V_STAR_MASS = Constants.M0 * 0.36;
  public static double M4V_STAR_MASS = Constants.M0 * 0.20;
  public static double M5V_STAR_MASS = Constants.M0 * 0.14;
  public static double M6V_STAR_MASS = Constants.M0 * 0.10;
  public static double M7V_STAR_MASS = Constants.M0 * 0.09;
  public static double M8V_STAR_MASS = Constants.M0 * 0.08;
  public static double M9V_STAR_MASS = Constants.M0 * 0.075;





  public static double G0V_STAR_TEMP = 5980;
  public static double G1V_STAR_TEMP = 5900;
  public static double G2V_STAR_TEMP = 5800;
  public static double G3V_STAR_TEMP = 5710;
  public static double G4V_STAR_TEMP = 5690;
  public static double G5V_STAR_TEMP = 5620;
  public static double G6V_STAR_TEMP = 5570;
  public static double G7V_STAR_TEMP = 5500;
  public static double G8V_STAR_TEMP = 5450;
  public static double G9V_STAR_TEMP = 5370;

  public static double M0V_STAR_TEMP = 3800;
  public static double M1V_STAR_TEMP = 3600;
  public static double M2V_STAR_TEMP = 3400;
  public static double M3V_STAR_TEMP = 3250;
  public static double M4V_STAR_TEMP = 3100;
  public static double M5V_STAR_TEMP = 2800;
  public static double M6V_STAR_TEMP = 2600;
  public static double M7V_STAR_TEMP = 2500;
  public static double M8V_STAR_TEMP = 2400;
  public static double M9V_STAR_TEMP = 2300;


	
	public static double G2V_STAR_LUMINOSITY = 3.846E26 * Unit.W.value();

  public static double M0V_STAR_LUMINOSITY = 0.072 * Constants.L0;
  public static double M1V_STAR_LUMINOSITY = 0.035 * Constants.L0;
  public static double M2V_STAR_LUMINOSITY = 0.023 * Constants.L0;
  public static double M3V_STAR_LUMINOSITY = 0.015 * Constants.L0;
  public static double M4V_STAR_LUMINOSITY = 0.0055 * Constants.L0;
  public static double M5V_STAR_LUMINOSITY = 0.0022 * Constants.L0;
  public static double M6V_STAR_LUMINOSITY = 0.0009 * Constants.L0;
  public static double M7V_STAR_LUMINOSITY = 0.0005 * Constants.L0;
  public static double M8V_STAR_LUMINOSITY = 0.0003 * Constants.L0;
  public static double M9V_STAR_LUMINOSITY = 0.00015 * Constants.L0;
	
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
