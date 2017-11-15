package org.spacedrones.utils.math;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertTest {

  @Test
  public void testConvertTime() {
    double decimalTime = 10.89;
    String stringResult = Convert.decimalTohhmmss(10.89);
    assertEquals("", decimalTime, Convert.hhmmssToDecimal(stringResult), 0.00001);
  }

}
