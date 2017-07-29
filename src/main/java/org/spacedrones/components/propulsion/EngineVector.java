package org.spacedrones.components.propulsion;

import java.util.Arrays;

public class EngineVector {
	
	public enum Axis {
		ROLL(0), 
		PITCH(1), 
		YAW(2);
		
		private final int index;
		Axis(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	private final double[] vectorComponents;

	public EngineVector(double[] vectorComponents) {
		super();
		this.vectorComponents = vectorComponents;
	}
	
	public EngineVector(double rollAxisComponent, double pitchAxisCompoent, double yawAxisComponent) {
		super();
		this.vectorComponents = new double[]{rollAxisComponent, pitchAxisCompoent, yawAxisComponent};
	}

	
	public double[] getVectorComponents() {
		return vectorComponents;
	}
	
	
	public double getVectorComponent(Axis axis) {
		return vectorComponents[axis.getIndex()];
	}
	

	@Override
	public String toString() {
		return "EngineVector [vectorComponents="
				+ Arrays.toString(vectorComponents) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(vectorComponents);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		EngineVector other = (EngineVector) obj;
		if (!Arrays.equals(vectorComponents, other.vectorComponents))
			return false;
		return true;
	}


	
	
	
	
	

}
