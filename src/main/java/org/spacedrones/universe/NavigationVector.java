package org.spacedrones.universe;

import java.math.BigDecimal;
import java.util.Arrays;

public class NavigationVector {
	
	private BigDecimal[] vectorComponents;

	public NavigationVector(BigDecimal[] vectorComponents) {
		super();
		this.vectorComponents = vectorComponents;
	}
	
	public NavigationVector(BigDecimal centerPointingAxisComponent, BigDecimal rotationPointingAxisCompoent, BigDecimal outOfPlanePointingAxisComponent) {
		super();
		this.vectorComponents = new BigDecimal[]{centerPointingAxisComponent, rotationPointingAxisCompoent, outOfPlanePointingAxisComponent};
	}

	public BigDecimal[] getVectorComponents() {
		return vectorComponents;
	}

	public void setVectorComponents(BigDecimal[] vectorComponents) {
		this.vectorComponents = vectorComponents;
	}

	@Override
	public String toString() {
		return Arrays.toString(vectorComponents);
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
		if (getClass() != obj.getClass())
			return false;
		NavigationVector other = (NavigationVector) obj;
		
		if(   this.vectorComponents[0].compareTo(other.vectorComponents[0]) !=0  )
			return false;
		if(this.vectorComponents[1].compareTo(other.vectorComponents[1]) !=0)
			return false;
		if(this.vectorComponents[2].compareTo(other.vectorComponents[2]) !=0)
			return false;
		
		return true;
	}
	
	
	
	

}
