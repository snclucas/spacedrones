package org.spacedrones.physics;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.data.PhysicsDataProvider;
import org.spacedrones.universe.CelestialConstants;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PhysicsTest {
	
	
	@Test
	public void testSubSpaceDispersion() {
		
		BigDecimal distance = new BigDecimal(1.5421430000000304E17);
		BigDecimal dispersion = new BigDecimal(0.25).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal calculatedDispersion = Physics.distanceToSubspaceSignalDispersion(distance);
		BigDecimal calculatedDistanceFromCalculatedDispersion = Physics.subspaceSignalDispersionToDistance(calculatedDispersion);
		
		assertEquals("Calculated distance from dispersion incorrect", Math.log10(distance.doubleValue()), Math.log10(calculatedDistanceFromCalculatedDispersion.doubleValue()), 0.000000000001);
		assertEquals("Calculated dispersion from distance incorrect", dispersion.doubleValue(), calculatedDispersion.doubleValue(), 0.001);
	}

	
	

	@Test
	public void testDecibelWatts() {
		double dBm = -50;
		double dB = -80;	
		double W = 1e-8;
		assertEquals("dBm to W conversion incorrect", W, Physics.dBm2W(dBm), 0.001);
		assertEquals("W to dBm conversion incorrect", dBm, Physics.W2dBm(W), 0.001);
		assertEquals("dB to W conversion incorrect", W, Physics.dB2W(dB), 0.001);
		assertEquals("W to dB conversion incorrect", dB, Physics.W2dB(W), 0.001);
	}
	
	
	
	@Test
	public void testStellarMagnitudeConversions() {
		
		double luminosityOfSolInLg = 1;
		double luminosityOfSolInW = CelestialConstants.G2V_STAR_LUMINOSITY;
		double absMagOfSol = 4.85;
		double calculatedAbsMagOfSol = Physics.luminosityInLg2AbsMag(luminosityOfSolInLg);
		double calculatedAbsMagOfSol2 = Physics.luminosityInW2AbsMag(luminosityOfSolInW);
		double calculatedLuminosityOfSol = Physics.absMag2LuminosityInLg(absMagOfSol);
		
		assertEquals("Luminosity to Absolute magnitude conversion incorrect for Sol", absMagOfSol, calculatedAbsMagOfSol, 0.001);
		assertEquals("Luminosity to Absolute magnitude conversion incorrect for Sol", absMagOfSol, calculatedAbsMagOfSol2, 0.001);
		assertEquals("Absolute magnitude to luminosity conversion incorrect for Sol", luminosityOfSolInLg, calculatedLuminosityOfSol, 0.001);
		
		
		double luminosityOfSirius = 23.988329190194904653173971349271;
		double absMagOfSirrius = 1.4;
		double calculatedAbsMagOfSirrius = Physics.luminosityInLg2AbsMag(luminosityOfSirius);
		double calculatedLuminosityOfSirrius = Physics.absMag2LuminosityInLg(absMagOfSirrius);
		
		assertEquals("Luminosity to Absolute magnitude conversion incorrect for Sirrius", absMagOfSirrius, calculatedAbsMagOfSirrius, 0.001);
		assertEquals("Absolute magnitude to luminosity conversion incorrect for Sirrius", luminosityOfSirius, calculatedLuminosityOfSirrius, 0.001);
	}
	
	
	
	@Test
	public void testLuminosityCalculations() {
		BigDecimal distance = new BigDecimal(10.0*Unit.Pc.value());
		double absMag = 4.85;
		double luminosity = Physics.absMag2LuminosityInW(absMag);
		
		double atdist = Physics.opticalSignalAtDistance(luminosity, distance, false);

		System.out.println(luminosity + " " + atdist);
		
		
	}
	
	
	@Test
	public void testAbsoluteAndApparentMagnitudes() {
		
		double apparentMagnitudeOfSol = -26.78029000682956;
		double absoluteMagnitudeOfSol = 4.85;
		
		// A star of absolute magnitude 4.85 will have an apparent magnitude of 4.85 at 10Pc
		double calculatedApparentMagnitudeOfSol = Physics.absoluteMagnitude2ApparentMagnitudeAtDistance(absoluteMagnitudeOfSol, new BigDecimal(10 * Unit.Pc.value()));
		
		// At 10pc a star of apparent magnitude -26.8  will have an absolute magnitude of -26.8 at 10Pc
		double calculatedAbsoluteMagnitudeOfSol = Physics.apparentMagnitude2AbsoluteMagnitude(apparentMagnitudeOfSol, 10 * Unit.Pc.value());
		
		assertEquals("Absolute to apparent magnitude incorrect", calculatedApparentMagnitudeOfSol, absoluteMagnitudeOfSol, 0.001);
		assertEquals("Apparent to absolute magnitude incorrect", calculatedAbsoluteMagnitudeOfSol, apparentMagnitudeOfSol, 0.001);
		
		// move an absolute magnitude of 4.85 to 4.72E-6Pc then its apparent magnitude should be -26.8
		assertEquals("Absolute to apparent magnitude incorrect", apparentMagnitudeOfSol, Physics.absoluteMagnitude2ApparentMagnitudeAtDistance(absoluteMagnitudeOfSol, new BigDecimal(4.72E-6 * Unit.Pc.value())), 0.001);
	}
	

	
	@Test
	public void testPowerLawSignalPropagationModels() {
		PhysicsDataProvider physicsDataProvider = Configuration.getPhysicsDataProvider();
		
		double distance = 1e10;
		double initialPower = 1e2;
		double opticalResult = Physics.opticalSignalAtDistanceInLog10(Math.log10(initialPower), new BigDecimal(distance));
		double RADARResult = Physics.radarSignalAtDistanceInLog10(Math.log10(initialPower), new BigDecimal(distance));
		double gravimetricResult = Physics.gravimetricSignalAtDistanceInLog10(Math.log10(initialPower), new BigDecimal(distance));
		double magnetoResult = Physics.magnetometricSignalAtDistanceInLog10(Math.log10(initialPower), new BigDecimal(distance));
		double subspaceResult = Physics.subspaceSignalAtDistanceInLog10(Math.log10(initialPower), new BigDecimal(distance));
		
		double opticalPowerIndex = physicsDataProvider.getValue(PhysicsDataProvider.OPTICAL_PROPAGATION_INDEX);
		double radarPowerIndex = physicsDataProvider.getValue(PhysicsDataProvider.RADAR_PROPAGATION_INDEX);
		double gravimetricPowerIndex = physicsDataProvider.getValue(PhysicsDataProvider.GRAVIMETRIC_PROPAGATION_INDEX);
		double magnetometricPowerIndex = physicsDataProvider.getValue(PhysicsDataProvider.MAGNETOMETRIC_PROPAGATION_INDEX);
		double subspacePowerIndex = physicsDataProvider.getValue(PhysicsDataProvider.SUBSPACE_PROPAGATION_INDEX);
		
		double expectedOptical = (Math.log10(initialPower) - (opticalPowerIndex*Math.log10(distance)));
		double expectedRADAR = (Math.log10(initialPower) - (radarPowerIndex*Math.log10(distance)));
		double expectedGravimetric = (Math.log10(initialPower) - (gravimetricPowerIndex*Math.log10(distance)));
		double expectedMagnetometric = (Math.log10(initialPower) - (magnetometricPowerIndex*Math.log10(distance)));
		double expectedSubspace = (Math.log10(initialPower) - (subspacePowerIndex*Math.log10(distance)));
		
		assertEquals("Optical signal not correct", expectedOptical, opticalResult, 0.001);
		assertEquals("RADAR signal not correct", expectedRADAR, RADARResult, 0.001);
		assertEquals("Gravimetric signal not correct", expectedGravimetric, gravimetricResult, 0.001);
		assertEquals("Magnetometric signal not correct", expectedMagnetometric, magnetoResult, 0.001);
		assertEquals("Subspace signal not correct", expectedSubspace, subspaceResult, 0.001);
		
		
		
	}

}
