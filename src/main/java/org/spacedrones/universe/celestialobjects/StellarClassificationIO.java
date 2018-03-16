package org.spacedrones.universe.celestialobjects;


import java.io.*;
import java.util.*;

import org.spacedrones.universe.data.*;

public class StellarClassificationIO {

  private List<StellarClassification> classifications;

  StellarClassificationIO() {
    classifications = new ArrayList<>();
  }

  private void readCSV() {
    String csvFile = "C:\\Users\\simon\\IdeaProjects\\spacedrones-latest\\data\\stellar_data.csv";
    BufferedReader br = null;
    String line;
    String cvsSplitBy = ",";

    try {

      br = new BufferedReader(new FileReader(csvFile));
      br.readLine();
      br.readLine();
      while ((line = br.readLine()) != null) {

        // use comma as separator
        String[] country = line.split(cvsSplitBy);
        if (country.length > 0) {
          StellarClassification sc = new StellarClassification(
                  country[0],
                  Double.parseDouble(country[1]),
                  Double.parseDouble(country[2]),
                  Double.parseDouble(country[3]),
                  Double.parseDouble(country[4]),
                  Double.parseDouble(country[5]),
                  Double.parseDouble(country[6]),
                  Double.parseDouble(country[7]),
                  Double.parseDouble(country[8]),
                  country[9]
          );
          classifications.add(sc);
        }

      }
      JSONUtils.serializeObject(classifications);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    new StellarClassificationIO().readCSV();
  }


}

class StellarClassification {
  public String type;
  public double mass;
  public double luminosity;
  public double radius;
  public double temp;
  public double colorIndex;
  public double absMag;
  public double boloCorr;
  public double boloMag;
  public String starColor;

  public StellarClassification(String type, double mass, double luminosity,
                               double radius, double temp, double colorIndex,
                               double absMag, double boloCorr, double boloMag, String starColor) {
    this.type = type;
    this.mass = mass;
    this.luminosity = luminosity;
    this.radius = radius;
    this.temp = temp;
    this.colorIndex = colorIndex;
    this.absMag = absMag;
    this.boloCorr = boloCorr;
    this.boloMag = boloMag;
    this.starColor = starColor;
  }
}