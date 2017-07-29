package org.spacedrones.profiles;

public class ThrustProfileFactory {
	
	public static String SIMPLE_LINEAR = "Simple Linear";

	public static ThrustProfile getThrustAlgorithm(String thrustModelType){
		if(thrustModelType.equalsIgnoreCase(SIMPLE_LINEAR)){
			return new SimpleLinearThrustProfile(SIMPLE_LINEAR);
		}
		return null;
	}

}
