package org.spacedrones.universe.data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.celestialobjects.StarClass;

import java.util.HashMap;
import java.util.Map;

public class JSONUtils {


  public static <T> void serializeObject(T object) {
    ObjectMapper objectMapper = new ObjectMapper();
    //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    try {
      System.out.println(objectMapper.writeValueAsString(object));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

  }


  public static void main(String[] args) {
    Star star = new Star(StarClass.K, SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.K));
    Star star2 = new Star(StarClass.A, SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.A));
    Coordinates coord = new Coordinates(0,0,0);

    Map<String, CelestialObject> stars = new HashMap<>();
    stars.put(star.id(), star);
    stars.put(star2.id(), star2);

    JSONUtils.serializeObject(stars);
  }


}
