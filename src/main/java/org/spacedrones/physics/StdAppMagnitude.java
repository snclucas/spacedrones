package org.spacedrones.physics;


public enum StdAppMagnitude {
  U(0.36 * Unit.um.value(), 0.15 * Unit.um.value(), 1810 * Unit.Jy.value()),
  B(0.44 * Unit.um.value(), 0.22 * Unit.um.value(), 4260 * Unit.Jy.value()),
  V(0.55 * Unit.um.value(), 0.16 * Unit.um.value(), 3640 * Unit.Jy.value()),
  R(0.64 * Unit.um.value(), 0.23 * Unit.um.value(), 3080 * Unit.Jy.value()),

  I(0.79 * Unit.um.value(), 0.19 * Unit.um.value(), 2550 * Unit.Jy.value()),
  J(1.26 * Unit.um.value(), 0.16 * Unit.um.value(), 1660 * Unit.Jy.value()),
  H(1.60 * Unit.um.value(), 0.23 * Unit.um.value(), 1080 * Unit.Jy.value()),
  K(2.22 * Unit.um.value(), 0.23 * Unit.um.value(), 670 * Unit.Jy.value()),
  g(0.52 * Unit.um.value(), 0.14 * Unit.um.value(), 3730 * Unit.Jy.value()),
  r(0.67 * Unit.um.value(), 0.14 * Unit.um.value(), 4490 * Unit.Jy.value()),
  i(0.79 * Unit.um.value(), 0.16 * Unit.um.value(), 4760 * Unit.Jy.value()),
  z(0.91 * Unit.um.value(), 0.13 * Unit.um.value(), 4810 * Unit.Jy.value());

  double peakWavelength;
  double bandWidth;
  double fluxInJy;

  StdAppMagnitude(double peakWavelength, double bandWidth, double fluxInJy) {
    this.peakWavelength = peakWavelength;
    this.bandWidth = bandWidth;
    this.fluxInJy = fluxInJy;
  }

}
