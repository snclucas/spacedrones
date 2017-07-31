package org.spacedrones.components.sensors;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class FractalSensorArray extends BasicSensorArray {


	public FractalSensorArray(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	

	@Override
	public String describe() {
		return "Fractal sensor array.";
	}
	
	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	
	
	
	
	public static TypeInfo type() {
		return new TypeInfo("FractalSensorArray");
	}


	@Override
	public TypeInfo getCategory() {
		return Sensor.category;
	}


	@Override
	public final TypeInfo getType() {
		return type();
	}

}
