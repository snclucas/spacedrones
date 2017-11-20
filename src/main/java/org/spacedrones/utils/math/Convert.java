package org.spacedrones.utils.math;

public class Convert {

  public static double RAInHoursToDegrees(int ra_h, int ra_m, double ra_s) {
    return (ra_h + ra_m/60.0 + ra_s / 3600.0)*15.0;
  }

  public static double hhmmssToDecimal(String hhmmss){
    String[] tokens = hhmmss.split(":");
    int hours = Integer.parseInt(tokens[0]);
    int minutes = Integer.parseInt(tokens[1]);
    double seconds = Double.parseDouble(tokens[2]);
    return ddmmssToDecimal(hours, minutes, seconds);
  }

  public static double ddmmssToDecimal(int dd, int mm, double ss){
    return dd + mm/60.0 + ss/3600.0;
  }

  public static String decimalTohhmmss(double decimalTime){
    int hours = (int) decimalTime;
    int minutes = (int) (decimalTime * 60) % 60;
    double seconds = (int) (decimalTime * (60*60)) % 60;
    return String.format("%s:%s:%s", hours, minutes, seconds);
  }


  private String intToStringDuration(int aDuration) {
    String result;

    int hours, minutes, seconds = 0;

    hours = aDuration / 3600;
    minutes = (aDuration - hours * 3600) / 60;
    seconds = (aDuration - (hours * 3600 + minutes * 60));

    result = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    return result;
  }
}
