package org.spacedrones.spacecraft;

import org.spacedrones.components.TypeInfo;

public class SpacecraftBus extends AbstractBus {

	public static TypeInfo typeID = new TypeInfo("SpacecraftBus");
	
	public SpacecraftBus(String name, Spacecraft spacecraft) {
		super(name, spacecraft);
	}

	@Override
	public TypeInfo getType() {
		return typeID;
	}

	@Override
	public String describe() {
		return "Simple spacecraft bus";
	}
	
}
