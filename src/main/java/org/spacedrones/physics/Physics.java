package org.spacedrones.physics;

import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.PowerLawSignalPropagationModel;
import org.spacedrones.data.PhysicsDataProvider;
import org.spacedrones.universe.CelestialConstants;
import org.spacedrones.utils.math.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Physics {
	
	
	private static PhysicsDataProvider physicsDataProvider = Configuration.getPhysicsDataProvider();

	public static BigDecimal subspaceSignalDispersionToDistance(BigDecimal signalDispersion) {
		signalDispersion = signalDispersion.setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		BigDecimal tenpc = new BigDecimal(10.0 * Unit.Pc.value(), Configuration.mc).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		return BigDecimalMath.sqrt(signalDispersion).multiply(tenpc, Configuration.mc); 
	}

	
	public static BigDecimal distanceToSubspaceSignalDispersion(BigDecimal distance) {
		BigDecimal index = new BigDecimal(2.0).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
		BigDecimal tenpc = new BigDecimal(10.0 * Unit.Pc.value()).setScale(Configuration.precision, BigDecimal.ROUND_HALF_UP);
    return BigDecimalMath.pow(distance.divide(tenpc, new MathContext(Configuration.precision, RoundingMode.HALF_DOWN)), index);
	}
	
	public static double dBm2W(double power_dBm) {
		return dB2W(power_dBm-30);
	}
	
	public static double dB2W(double power_dB) {
		return 1*Math.pow(10,power_dB/10.0);
	}
	
	public static double W2dB(double power_W) {
		return 10*Math.log10(power_W/1.0);
	}
	
	public static double W2dBm(double power_W) {
		return W2dB(power_W)+30;
	}
	
	
	// Stellar magnitudes and luminosity
	
	public static double absMag2LuminosityInLg(double absMag) {
		return Math.pow(10, (-0.4)*((absMag-4.85))  ) ;
	}
	
	public static double absMag2LuminosityInW(double absMag) {
		return Math.pow(10, (-0.4)*((absMag-4.85))  ) * CelestialConstants.G2V_STAR_LUMINOSITY;
	}
	
	
	
	public static double luminosityInW2AbsMag(double luminosity) {
		double luminosityInLg = luminosity / CelestialConstants.G2V_STAR_LUMINOSITY;
		return 4.85 - ( 2.5 * (  Math.log10(luminosityInLg)  ) ) ;
	}

  public static void main(String[] args) {
    System.out.println(luminosityInW2AbsMag(CelestialConstants.G2V_STAR_LUMINOSITY));
    System.out.println(CelestialConstants.G2V_STAR_LUMINOSITY /  (4 * Math.PI * 1*Unit.AU.value()*Unit.AU.value()));
  }

  public static double luminosityInLg2AbsMag(double luminosity) {
		return 4.85 - ( 2.5 * (  Math.log10(luminosity)  ) ) ;
	}

	
	public static double absoluteMagnitude2ApparentMagnitudeAtDistance(double absMag, BigDecimal distance) {
		if(distance.compareTo(new BigDecimal(1e-50)) < 0)
			return absMag;
		double distanceInPc = distance.divide(new BigDecimal(Unit.Pc.value()), Configuration.mc).doubleValue();
		double distanceModulous = 5.0 * Math.log10(distanceInPc) - 5.0;
		return distanceModulous + absMag;
	}
	
	
	public static double apparentMagnitude2AbsoluteMagnitude(double appMag, double distance) {
		double distanceInPc = distance / Unit.Pc.value();
		double distanceModulous = 5.0 * Math.log10(distanceInPc) - 5.0;
		return appMag - distanceModulous;
	}
	
	
	public static double opticalSignalAtDistance(double p0, BigDecimal distance, boolean perMeter) {
	  double surfaceArea = perMeter ? 4*Math.PI*distance.doubleValue()*distance.doubleValue() : 1.0;
		return Math.pow(10, new PowerLawSignalPropagationModel(physicsDataProvider.getValue(
						PhysicsDataProvider.OPTICAL_PROPAGATION_INDEX)).getSignal(Math.log10(p0), distance)) / surfaceArea;
	}
	
	public static double opticalSignalAtDistanceInLog10(double p0, BigDecimal distance) {
		return new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.OPTICAL_PROPAGATION_INDEX)).getSignal(p0, distance);
	}
	
	
	public static double radarSignalAtDistance(double p0, BigDecimal distance) {
		return Math.pow(10, new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.RADAR_PROPAGATION_INDEX)).getSignal(Math.log10(p0), distance));
	}
	
	public static double radarSignalAtDistanceInLog10(double p0, BigDecimal distance) {
		return new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.RADAR_PROPAGATION_INDEX)).getSignal(p0, distance);
	}
	
	
	public static double gravimetricSignalAtDistance(double p0, BigDecimal distance) {
		return Math.pow(10, new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.GRAVIMETRIC_PROPAGATION_INDEX)).getSignal(Math.log10(p0), distance));
	}
	
	public static double gravimetricSignalAtDistanceInLog10(double p0, BigDecimal distance) {
		return new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.GRAVIMETRIC_PROPAGATION_INDEX)).getSignal(p0, distance);
	}
	
	
	public static double magnetometricSignalAtDistance(double p0, BigDecimal distance) {
		return Math.pow(10, new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.MAGNETOMETRIC_PROPAGATION_INDEX)).getSignal(Math.log10(p0), distance));
	}
	
	public static double magnetometricSignalAtDistanceInLog10(double p0, BigDecimal distance) {
		return new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.MAGNETOMETRIC_PROPAGATION_INDEX)).getSignal(p0, distance);
	}
	
	
	public static double subspaceSignalAtDistance(double p0, BigDecimal distance) {
		return Math.pow(10, new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.SUBSPACE_PROPAGATION_INDEX)).getSignal(Math.log10(p0), distance));
	}
	
	public static double subspaceSignalAtDistanceInLog10(double p0, BigDecimal distance) {
		return new PowerLawSignalPropagationModel(physicsDataProvider.getValue(PhysicsDataProvider.SUBSPACE_PROPAGATION_INDEX)).getSignal(p0, distance);
	}

}
