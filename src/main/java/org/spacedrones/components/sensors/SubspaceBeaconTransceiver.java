package org.spacedrones.components.sensors;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.navigation.BeaconSignal;
import org.spacedrones.physics.Physics;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.utils.Utils;
import org.spacedrones.utils.math.DistanceSolver;

public class SubspaceBeaconTransceiver extends AbstractSensor implements PositioningSensor {

	public static TypeInfo typeID = new TypeInfo("SubspaceBeaconTransceiver");
	
	private static MathContext context = new MathContext(120, RoundingMode.HALF_UP);

	protected List<BeaconSignal> beaconSignals;
	SensorResponseMediator sensorResponseMediator;

	public SubspaceBeaconTransceiver(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile) {
		super(name, busResourceSpecification, sensorProfile);

		beaconSignals = new ArrayList<BeaconSignal>();
		sensorResponseMediator = Configuration.getSensorResponseMediator();
	}

	@Override
	public TypeInfo getType() {
		return typeID;
	}


	@Override
	public List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile) {
		return super.passiveScan(duration, sensorProfile);
	}




	@Override
	public double getSensorGain() {
		return 1;
	}


	@Override
	public double getCurrentPower() {
		return getNominalPower();
	}


	@Override
	public double getCurrentCPUThroughput() {
		return getNominalCPUThroughput();
	}



	@Override
	public SystemStatus online() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override 
	public Coordinates calculatePosition() {
		
	//	sensorResponseMediator.passiveScan(66, 2, 1, Sensor.SUBSPACE_RESONANCE);

		if(beaconSignals.size() < 3 )
			return new Coordinates(Coordinates.NOT_KNOWN);

		BigDecimal[] distances = new BigDecimal[beaconSignals.size()];

		int cntr = 0;
		for(BeaconSignal bSignal : beaconSignals) {
			distances[cntr] = Physics.subspaceSignalDispersionToDistance(new BigDecimal(bSignal.getSignalDispersion()));
			cntr++;
		}

		final BigDecimal x1 = getBeaconCoordinate(0, 0, Unit.Pc.value());
		final BigDecimal y1 = getBeaconCoordinate(0, 1, Unit.Pc.value());
		final BigDecimal z1 = getBeaconCoordinate(0, 2, Unit.Pc.value());

		final BigDecimal x2 = getBeaconCoordinate(1, 0, Unit.Pc.value());
		final BigDecimal y2 = getBeaconCoordinate(1, 1, Unit.Pc.value());
		final BigDecimal z2 = getBeaconCoordinate(1, 2, Unit.Pc.value());

		final BigDecimal x3 = getBeaconCoordinate(2, 0, Unit.Pc.value());
		final BigDecimal y3 = getBeaconCoordinate(2, 1, Unit.Pc.value());
		final BigDecimal z3 = getBeaconCoordinate(2, 2, Unit.Pc.value());


		final BigDecimal R1 = distances[0].abs().divide(new BigDecimal(Unit.Pc.value()), context);
		final BigDecimal R2 = distances[1].abs().divide(new BigDecimal(Unit.Pc.value()), context);	
		final BigDecimal R3 = distances[2].abs().divide(new BigDecimal(Unit.Pc.value()), context);

		double[] pos = DistanceSolver.solve(1e-10, x1.doubleValue(), y1.doubleValue(), z1.doubleValue(), R1.doubleValue(), 
				x2.doubleValue(), y2.doubleValue(), z2.doubleValue(), R2.doubleValue()
				, x3.doubleValue(), y3.doubleValue(), z3.doubleValue(), R3.doubleValue());

		//	BigDecimal[] posBig = DistanceSolver.solve(new BigDecimal(1e-12), x1, y1, z1, R1, 
		//			x2, y2, z2, R2, x3, y3, z3, R3);

		return new Coordinates(Utils.doubleArrayToBigDecimalArray(pos));
	}


	private BigDecimal getBeaconCoordinate(int beacon, int index, double unit) {
		return beaconSignals.get(beacon).getBeacon().getCoordinates().get(index).divide(new BigDecimal(unit), context);
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}



	public void activate(double duration) {





	}

	@Override
	public String describe() {
		return "Subspace beacon transciever: A device capable of detecting subspace harmonic distortions usually created by subspace beacons.";
	}

	
	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}


}


