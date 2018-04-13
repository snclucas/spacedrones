package org.spacedrones.consumables;


import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftDataProvider;

public class FuelOxidizerMixFactory {

  public static int HYDROGEN_OXYGEN = 0;

  private static SpacecraftDataProvider localSpacecraftDataProvider = Configuration.getSpacecraftDataProvider();

  public static FuelOxMix getFuelOxidizerMix(final int mixType, final double fuelMassFraction, final double oxidizerMassFraction) {
    if(mixType == HYDROGEN_OXYGEN) {
      FuelConstituent hydrogen = localSpacecraftDataProvider.getLiquid(Fuel.LIQUID_HYDROGEN);
      FuelConstituent oxygen = localSpacecraftDataProvider.getLiquid(Oxidizer.LIQUID_OXYGEN);
      return new FuelOxMix(hydrogen, fuelMassFraction, oxygen, oxidizerMassFraction);
    } else {
      throw new IllegalArgumentException("No mix");
    }

  }

  public static FuelOxMix getFuelOxidizerMix(final int mixType, final double oxidizerFuelRatio) {
    double fuelMassFraction = 1/(oxidizerFuelRatio + 1);
    double oxidizerMassFraction = 1 - fuelMassFraction;
    return getFuelOxidizerMix(mixType, fuelMassFraction, oxidizerMassFraction);
  }

}
