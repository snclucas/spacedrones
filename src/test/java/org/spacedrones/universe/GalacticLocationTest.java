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

    double RA = (17 + 57.0/60.0 + 48.515 / 3600.0)*15.0;

    System.out.println(RA);
		System.out.println(dec + " " + dec2);
	  double[] results = GalacticLocation.convertEquatorialToGalactic(RA, dec, Unit.degrees);


    System.out.println(results[0] + " " + results[1]);

	  //gLoc.convertGalacticToEquatorial();





	}
	
	


}
