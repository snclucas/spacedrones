package org.spacedrones;


public interface Signature {
  String getOpticalSignature();
  String getRadarSignature();
  String getGravimetricSignature();
  String getMagnetometricSignature();
  String getSubspaceResonanceSignature();
}
