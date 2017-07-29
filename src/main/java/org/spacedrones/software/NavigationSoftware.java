package org.spacedrones.software;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.DataStore;
import org.spacedrones.components.sensors.PositioningSensor;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorResult;
import org.spacedrones.navigation.NavigationInterface;
import org.spacedrones.universe.Coordinates;

public class NavigationSoftware extends AbstractSoftware implements Software, NavigationInterface {
	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	private DataStore dataStore;
	
	public static TypeInfo type() {
		return new TypeInfo("NavigationSoftware");
	}

	private final Map<String, Sensor> sensors = new HashMap<String, Sensor>();
	private final List<SensorResult> sensorResults = new ArrayList<SensorResult>();
	
	public NavigationSoftware(String name) {
		super(name);
		this.dataStore = getSystemComputer().getStorageDevice();
	}

	public NavigationSoftware(String name, Computer computer) {
		super(name);
		populateSensors();
		this.dataStore = getSystemComputer().getStorageDevice();
	}
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}


	private void populateSensors() {
		List<Sensor> sensorList = getSensors();
		for(Sensor sensor : sensorList)
			sensors.put(sensor.getIdent(), sensor);
	}



	@Override
	public String getDescription() {
		return "Engine management";
	}

	@Override
	public String toString() {
		return getDescription() + " software";
	}


	public List<SensorResult> scanAll(){
		List<Sensor> sensors = getSensors();
		for(Sensor sensor : sensors) 
			sensorResults.addAll(scan(sensor.getIdent()));
		dataStore.saveData(sensorResults);
		return sensorResults;
	}


	public List<SensorResult> scan(String sensorIdent){
		Sensor sensor = sensors.get(sensorIdent);
		List<SensorResult> sensorResults = sensor.passiveScan(10.0, sensor.getSensorProfile());
		sensorResults.addAll(sensorResults);
		dataStore.saveData(sensorResults);
		return sensorResults;
	}


	private List<Sensor> getSensors() {
		List<SpacecraftBusComponent> components = getSystemComputer()
				.findComponentByCategory(Sensor.category());
		List<Sensor> sensors = new ArrayList<Sensor>();
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
		List<Coordinates> coordinates = new ArrayList<Coordinates>();
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

	@Override
	public String describe() {
		return "Software to perform navigation functions.";
	}


}
