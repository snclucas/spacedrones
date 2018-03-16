package org.spacedrones.universe.celestialobjects;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spacedrones.universe.data.JSONUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StellarClassificationIO {

  private List<StellarClassification> classifications;

  private StellarClassificationIO() {
    classifications = new ArrayList<>();
  }

  private void readCSV() {
    String csvFile = Thread.currentThread().getContextClassLoader().getResource("stellar_data.csv").getFile();
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

  public static StellarClassification getStellarClassification(String starClass) {
    StellarClassification stellarClassification = null;

    File file = new File(Thread.currentThread().getContextClassLoader().getResource("stellar_data.json").getFile());
      ObjectMapper mapper = new ObjectMapper();
      try {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<StellarClassification> myObjects = mapper.readValue(file, new TypeReference<List<StellarClassification>>() {});

        Optional<StellarClassification> classificationFromLibrary = myObjects
                .stream()
                .filter(o -> o.type.toUpperCase().equals(starClass.toUpperCase())).findAny();

        stellarClassification = classificationFromLibrary.orElseThrow(() -> new NoSuchElementException("Stellar classification not in library"));
      } catch (IOException e) {
        e.printStackTrace();
      }

    return stellarClassification;
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

  public StellarClassification() {

  }

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