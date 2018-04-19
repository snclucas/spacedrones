package org.spacedrones.materials;


public class Gas extends AbstractSubstance implements Fluid {

  public static int ACETHYLENE = "Acethylene".hashCode();
  public static int AMMONIA = "Ammonia".hashCode();
  public static int ARGON = "Argon".hashCode();
  public static int NITROGEN = "Nitrogen".hashCode();
  public static int NITROGEN_OXIDE = "Nitrogen Oxide".hashCode();
  public static int BUTANE = "Butane".hashCode();
  public static int I_BUTANE = "i-Butane".hashCode();
  public static int ETHANE = "Ethane".hashCode();
  public static int ETHYLENE = "Ethylene".hashCode();
  public static int ETHYL_ETHER	= "Ethyl Ether".hashCode();
  public static int ETHYL_CHLORIDE  =	"Ethyl Chloride".hashCode();
  public static int HELIUM = "Helium".hashCode();
  public static int CHLORINE = "Chlorine".hashCode();
  public static int HYDROGEN_CHLORIDE = "Hydrogen Chloride".hashCode();
  public static int OXYGEN = "Oxygen".hashCode();
  public static int KRYPTON = "Krypton".hashCode();
  public static int XENON = "Xenon".hashCode();
  public static int METHANE = "Methane".hashCode();
  public static int METHYL_CHLORIDE = "Methyl Chloride".hashCode();
  public static int NEON = "Neon".hashCode();
  public static int OZONE = "Ozone".hashCode();
  public static int PENTANE = "Pentane".hashCode();
  public static int PROPANE = "Propane".hashCode();
  public static int PROPENE = "Propene".hashCode();
  public static int SULPHUR_DIOXIDE = "Sulphur Dioxide".hashCode();
  public static int SULPHUR_HYDROGEN = "Sulphur Hydrogen".hashCode();
  public static int CARBON_DIOXIDE = "Carbon Dioxide".hashCode();
  public static int CARBON_MONOXIDE = "Carbon Monoxide".hashCode();
  public static int AIR = "Air".hashCode();
  public static int HYDROGEN = "Hydrogen".hashCode();


  public Gas(final String name, final double density, final double gamma, final double molecularWeight) {
    super(name, density, gamma, molecularWeight);
  }

}
