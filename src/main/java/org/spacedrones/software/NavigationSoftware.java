package org.spacedrones.software;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.computers.*;
import org.spacedrones.components.sensors.PositioningSensor;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorResult;
import org.spacedrones.navigation.NavigationInterface;
import org.spacedrones.universe.Coordinates;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class NavigationSoftware extends AbstractSoftware implements Software, NavigationInterface {

	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);

	private DataStore dataStore;

	private final Map<String, Sensor> sensors = new HashMap<>();
	private final List<SensorResult> sensorResults = new ArrayList<>();

	public NavigationSoftware(String name) {
		super(name);
		this.dataStore = getSystemComputer().orElseThrow(() ->
            new NoSuchElementException(this.getDescription() + ": No system computer")).getStorageDevice();
	}

	public NavigationSoftware(String name, Computer computer) {
		super(name);
		populateSensors();
		this.dataStore = getSystemComputer().orElseThrow(() ->
            new NoSuchElementException(this.getDescription() + ": No system computer")).getStorageDevice();
	}

	private void populateSensors() {
		List<Sensor> sensorList = getSensors();
		for(Sensor sensor : sensorList)
			sensors.put(sensor.id(), sensor);
	}

	@Override
	public String getDescription() {
		return "Engine management";
	}

	@Override
	public String toString() {
		return getDescription() + " software";
	}


	public List<SensorResult> scan(String sensorIdent){
		Sensor sensor = sensors.get(sensorIdent);
		List<SensorResult> sensorResults = sensor.passiveScan(10.0, sensor.getSensorProfile());
		sensorResults.forEach(sr ->
            dataStore.saveData(new DataRecord<SensorResult>("d", SensorResult.class.getSimpleName(), sr)));
		return sensorResults;
	}

	private List<Sensor> getSensors() {
		List<Sensor> components = getSystemComputer().orElseThrow(() ->
            new NoSuchElementException(this.getDescription() + ": No system computer"))
				.findComponentByType(Sensor.class);
		List<Sensor> sensors = new ArrayList<>();
		for(SpacecraftBusComponent sensor : components)
			sensors.add((Sensor)sensor);
		return sensors;
	}

	@Override
	public void getVectorToCoordinates(Coordinates coordinates) {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinates getSpacecraftLocation() {
		if(hasPositioningSensors() > 0)
			return new Coordinates();
		List<Coordinates> coordinates = new ArrayList<>();
		for(Sensor sensor : getSensors())
			if(sensor instanceof PositioningSensor)
				coordinates.add(((PositioningSensor)sensor).calculatePosition());
		return processPositioningSensorData(coordinates);
	}



	private Coordinates processPositioningSensorData(List<Coordinates> coordinates) {
		BigDecimal X = BigDecimal.ZERO;
		BigDecimal Y = BigDecimal.ZERO;
		BigDecimal Z = BigDecimal.ZERO;
		for(int i = 0; i<=coordinates.size();i++) {
			X = X.add(coordinates.get(i).get(0));
			Y = Y.add(coordinates.get(i).get(1));
			Z = Z.add(coordinates.get(i).get(2));
		}
		BigDecimal numberOfPositionsReturned = new BigDecimal(coordinates.size());
		X = X.divide(numberOfPositionsReturned, mc);
		Y = Y.divide(numberOfPositionsReturned, mc);
		Z = Z.divide(numberOfPositionsReturned, mc);
		return new Coordinates(X, Y, Z);
	}


	public int hasPositioningSensors() {
		int cnt = 0;
		for(Sensor sensor : getSensors())
			if(sensor instanceof PositioningSensor)
				cnt++;
		return cnt;
	}

}
