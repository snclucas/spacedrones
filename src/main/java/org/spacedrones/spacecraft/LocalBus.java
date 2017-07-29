package org.spacedrones.spacecraft;

import org.spacedrones.components.TypeInfo;

public class LocalBus extends AbstractBus {
	
	public static TypeInfo typeID = new TypeInfo("LocalBus");

	public LocalBus(String name, Spacecraft spacecraft) {
		super(name, spacecraft);
	}

	@Override
	public TypeInfo getType() {
		return typeID;
	}

	@Override
	public String describe() {
		return "Local loopback bus";
	}

}
