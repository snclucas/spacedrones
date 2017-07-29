package org.spacedrones.components.energygeneration;

import org.spacedrones.components.Component;
import org.spacedrones.components.TypeInfo;

public interface PowerGenerator extends Component{
	
	public static TypeInfo category() {
		return new TypeInfo("PowerGenerator");
	}
	
	public double getPowerOutput();
	public double getMaximumPowerOutput();

}
