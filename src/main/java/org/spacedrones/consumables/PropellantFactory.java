package org.spacedrones.consumables;


import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.materials.Liquid;

public class PropellantFactory {

  public static int LH2_LOX = 0;
  public static int HYDROLOX = 0;
  public static int CH4_LOX = 1;
  public static int C2H6_LOX = 2;

  private static SpacecraftDataProvider dataProvider = Configuration.getSpacecraftDataProvider();

  public static Propellant getPropellant(final int mixType, final double fuelMassFraction, final double oxidizerMassFraction) {
    if (mixType == LH2_LOX) {
      /*
      Propellant hydroloxVacuum = new Bipropellant(
            "Hydrolox-vacuum",
            dataProvider.getLiquid(LiquidFuel.LIQUID_HYDROGEN),
            dataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN),
            4462,	4.83,	2978,	2386);
      */
      return new Bipropellant(
              "Hydrolox-1atm",
              dataProvider.getLiquid(Liquid.LIQUID_HYDROGEN),
              dataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN),
              3816, 4.13, 2740, 2416, 381);
    } else if (mixType == CH4_LOX) {

      /*
Propellant CH4Vacuum = new Bipropellant(
            "CH4-vacuum",
            dataProvider.getLiquid(LiquidFuel.LIQUID_METHANE),
            dataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN),
            3615,	3.45,	3290,	1838);
      */

      return new Bipropellant(
              "CH4-1atm",
              dataProvider.getLiquid(Liquid.LIQUID_METHANE),
              dataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN),
              3034, 3.21, 3260, 1857, 299);

    } else if (mixType == C2H6_LOX) {

      /*
Propellant C2H6Vacuum = new Bipropellant(
            "C2H6-vacuum",
            dataProvider.getLiquid(LiquidFuel.LIQUID_ETHANE),
            dataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN),
            3584, 3.10, 3351, 1825);
      */

      return new Bipropellant(
              "C2H6-1atm",
              dataProvider.getLiquid(Liquid.LIQUID_ETHANE),
              dataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN),
              3006, 2.89, 3320, 1840, 269);

    } else {
      throw new IllegalArgumentException("No mix");
    }

  }


  public static Propellant getPropellant(final int mixType, final double oxidizerFuelRatio) {
    double fuelMassFraction = 1/(oxidizerFuelRatio + 1);
    double oxidizerMassFraction = 1 - fuelMassFraction;
    return getPropellant(mixType, fuelMassFraction, oxidizerMassFraction);
  }

}
