package org.spacedrones.universe;

import java.math.BigDecimal;
import java.util.Arrays;

import org.spacedrones.Configuration;
import org.spacedrones.physics.Unit;

public class Coordinates {

	public static BigDecimal[] NOT_KNOWN = new BigDecimal[]{new BigDecimal(-1), new BigDecimal(-1), new BigDecimal(-1)};
	
	private BigDecimal[] location;
	
	
	public Coordinates() {
		super();
		this.location = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
	}

	
	public Coordinates(BigDecimal ... coords) {
		super();
		this.location = coords;
	}
	

	public BigDecimal[] getCoordinates() {
		return location;
	}
	
	
	public BigDecimal get(int index) {
		return location[index];
	}
	

	public BigDecimal get(int index, double unit) {
		return location[index].divide(new BigDecimal(unit), Configuration.mc);
	}
	
	
	public Coordinates add(Coordinates coordinates) {
		return new Coordinates( 
				new BigDecimal[]{
						this.location[0].add(coordinates.get(0)), 
						this.location[1].add(coordinates.get(1)), 
						this.location[2].add(coordinates.get(2))
						});
	}
	
	
	public Coordinates addDistance(BigDecimal[] distance) {
		return new Coordinates( 
				new BigDecimal[]{
						this.location[0].add(distance[0]), 
						this.location[1].add(distance[1]), 
						this.location[2].add(distance[2])
						});
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(location);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Coordinates other = (Coordinates) obj;
		if (!Arrays.equals(location, other.location))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String location0 = location[0].toString();
		if(location[0].compareTo(new BigDecimal(1* Unit.Ly.value()))>0)
			location0 = (location[0].divide(new BigDecimal(1.0*Unit.Ly.value()), Configuration.mc)).toString() + "Ly";
		String location1 = location[1].toString();
		if(location[1].compareTo(new BigDecimal(1* Unit.Ly.value()))>0)
			location1 = (location[1].divide(new BigDecimal(1.0*Unit.Ly.value()), Configuration.mc)).toString() + "Ly";
		String location2 = location[2].toString();
		if(location[2].compareTo(new BigDecimal(1* Unit.Ly.value()))>0)
			location2 = (location[2].divide(new BigDecimal(1.0*Unit.Ly.value()), Configuration.mc)).toString() + "Ly";
		
		return location0 + ", " + location1 + ", " + location2;
	}
	

}
