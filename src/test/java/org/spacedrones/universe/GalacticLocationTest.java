package org.spacedrones.universe;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.utils.math.Convert;

public class GalacticLocationTest {

	@Test
	public void testConverters() {

		//Right Ascension and Declination: 17h57m48.515s, +4°41'35.83" (epoch 2000.0)

    //Distance from Sol: 5.941 light-years (1.821 parsecs)
    //Celestial (X,Y,Z) coordinates in ly: -0.0566, -5.920, 0.486
    //Galactic (X,Y,Z) coordinates in ly: 4.936, 3.001, 1.388

    //Equatorial
    //Right Ascension; 17 hr, 57 min, 48.5 sec - 5.325t sec.
    //Declination; +4 deg, 41 min, 36 sec +1033.777t arc-sec.

    //Galactic Latitude; +14.0627 deg +882.822t arc-sec.
    //Galactic Longitude; 30.867 deg - 543.78t arc-sec.

    //double RA = Convert.hhmmssToDecimal("17:57:48.515");
    double dec = Convert.hhmmssToDecimal("4:41:35.83");

    double dec2 = 4 + 41.0/60.0 + 36.0 / 3600.0;

    double RA = Convert.RAInHoursToDegrees(17, 57, 48.515);

    System.out.println(RA);
		System.out.println(dec + " " + dec2);
	  double[] results = GalacticLocation.convertEquatorialToGalactic(RA, dec, Unit.degrees);


    System.out.println(results[0] + " " + results[1]);


    double res[] = GalacticLocation.convertEquatorialToCartesian(0.00006, 1.089009, Unit.degrees, 219.7802, Unit.Ly);
    System.out.println(res[0] + " " + res[1] + " " + res[2]);

    double res2[] = GalacticLocation.convertEquatorialToCartesian(0.000283, -19.49884, Unit.degrees, 47.9616, Unit.Ly);
    System.out.println(res2[0] + " " + res2[1] + " " + res2[2]);

    //results = GalacticLocation.convertGalacticToEquatorial(228.81250000, 87.45000000, Unit.degrees);
    //assertEquals("", 190.12099977, results[0], 0.0001);
    //assertEquals("", 26.40401856, results[1], 0.0001);
	  //gLoc.convertGalacticToEquatorial();





	}
	
	


}
