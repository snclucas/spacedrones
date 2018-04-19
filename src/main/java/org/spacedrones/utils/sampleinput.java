package org.spacedrones.utils;


public class sampleinput {
  public static int numSamples=18;
  public static String[] samples = new String[numSamples];
  public static String[] sampleNames = new String[numSamples];


  public sampleinput() {
  }

  public static void setSamples() {
    samples = new String[numSamples];


    sampleNames[0]="5000 lbs 10% dry mass 5 km/s";
    samples[0] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.10 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 5.0";
    sampleNames[1]="5000 lbs 12% dry mass 5 km/s";
    samples[1] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.12 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 5.0";
    sampleNames[2]="5000 lbs 13% dry mass 5 km/s";
    samples[2] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.13 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 5.0";
    sampleNames[3]="5000 lbs 14% dry mass 5 km/s";
    samples[3] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.14 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 5.0";
    sampleNames[4]="5000 lbs 16% dry mass 5 km/s";
    samples[4] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.16 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 5.0";
    sampleNames[5]="5000 lbs 14% dry mass 6 km/s";
    samples[5] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.14 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 6.0";
    sampleNames[6]="5000 lbs 16% dry mass 6 km/s";
    samples[6] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.16 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 6.0";
    sampleNames[7]="5000 lbs 4% dry mass 9.65 km/s";
    samples[7] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.04 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 9.65";
    sampleNames[8]="5000 lbs 4% dry mass 11 km/s";
    samples[8] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.04 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 11";
    sampleNames[9]="5000 lbs 4% dry mass 5 km/s";
    samples[9] =
            "WeightLBS 5000 \n" +
                    "ISP    350 \n" +
                    "DryMassFraction 0.04 \n" +
                    "FuelCostPerLB   0.16\n" +
                    "DeltaVkps 5.0";
    sampleNames[10]="Pressure Tank - Cylinder";
    samples[10] =
            "/* Looking at heat resistant \n" +
                    " http://www.haynesintl.com/mini/230Site/230.htm \n" +
                    " */ \n" +
                    "TankPSI 300 \n" +
                    "TankMegaPascals 860\n" +
                    "TankGramsPerCC   8 \n" +
                    "TankRadiusMeters 0.3 \n" +
                    "TankCylinderMeters 4.12";
    sampleNames[11]="Pressure Tank - Sphere";
    samples[11] =
            "TankPSI 300 \n" +
                    "TankMegaPascals 860\n" +
                    "TankGramsPerCC   8 \n" +
                    "TankRadiusMeters 0.6732\n" +
                    "TankCylinderMeters 0";
    sampleNames[12]="Pressure Tank - Combo";
    samples[12] =
            "TankPSI 300 \n" +
                    "TankMegaPascals 860\n" +
                    "TankGramsPerCC   8 \n" +
                    "TankRadiusMeters 0.5 \n" +
                    "TankCylinderMeters 0.948";
    sampleNames[13]="Pressure Tank - Aluminum";
    samples[13] =
            "TankPSI 300 \n" +
                    "TankMegaPascals 200\n" +
                    "TankGramsPerCC   2.7 \n" +
                    "TankRadiusMeters 0.5 \n" +
                    "TankCylinderMeters 0.948";
    sampleNames[14]="Pressure Tank - Titanium";
    samples[14] =
            "/* http://www.titanium.com/tech_manual/tech5.cfm */ \n" +
                    "TankPSI 300 \n" +
                    "TankMegaPascals 1300\n" +
                    "TankGramsPerCC   4.65 \n" +
                    "TankRadiusMeters 0.5 \n" +
                    "TankCylinderMeters 0.948";
    sampleNames[15]="Tether Mass Ratio - Tensile etc";
    samples[15] =
            "TensileGPa 3.51 \n" +
                    "Density 970 \n" +
                    "SafetyFactor 2.0 \n" +
                    "DeltaVkps 4.3 \n";
    sampleNames[16]="Tether Mass Ratio - Char Vel etc";
    samples[16] =
            "CharacteristicVelocity 1200 \n" +
                    "DeltaV 4300 \n";
    sampleNames[17]="Speed of sound in tether";
    samples[17] =
            "ModulusGPa 124 \n" +
                    "Density 970 \n";
  }
}