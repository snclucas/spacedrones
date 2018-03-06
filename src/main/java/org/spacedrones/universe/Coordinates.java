package org.spacedrones.universe;

import org.spacedrones.Configuration;
import org.spacedrones.physics.Unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Coordinates {

	public static BigDecimal[] NOT_KNOWN = new BigDecimal[]{new BigDecimal(-1), new BigDecimal(-1), new BigDecimal(-1)};

	private final BigDecimal[] location;


	public Coordinates() {
		this.location = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
	}

	public Coordinates(BigDecimal ... coords) {
		if(coords.length != 3)
			throw new IllegalArgumentException("Need 3 coordinates");
		this.location = coords;
	}

	public Coordinates(double ... coords) {
		if(coords.length != 3)
			throw new IllegalArgumentException("Need 3 coordinates");
		this.location = new BigDecimal[]{new BigDecimal(coords[0]), new BigDecimal(coords[1]), new BigDecimal(coords[2])};
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
						this.location[0].add(coordinates.get(0)),
						this.location[1].add(coordinates.get(1)),
						this.location[2].add(coordinates.get(2)));
	}

	public Coordinates addDistance(BigDecimal[] distance) {
		return new Coordinates(
						this.location[0].add(distance[0]),
						this.location[1].add(distance[1]),
						this.location[2].add(distance[2]));
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
	  if(this.getClass() != obj.getClass())
	    return false;
		if (this == obj)
			return true;
		Coordinates other = (Coordinates) obj;
    return Arrays.equals(location, other.location);
  }

	@Override
	public String toString() {
		return formatCoordinateOutput(location[0]) + ", " + formatCoordinateOutput(location[1]) + ", " + formatCoordinateOutput(location[2]);
	}

	private String formatCoordinateOutput(BigDecimal location) {
	  if(location.compareTo(new BigDecimal(1000* Unit.kLy.value()))>0)
      return (location.divide(new BigDecimal(1.0*Unit.kLy.value()), Configuration.mc)).setScale(2, RoundingMode.CEILING).toEngineeringString() + " kLy";
    else if(location.compareTo(new BigDecimal(.1* Unit.Ly.value()))>0)
      return (location.divide(new BigDecimal(1.0*Unit.Ly.value()), Configuration.mc)).setScale(8, RoundingMode.CEILING).toEngineeringString() + " Ly";
		else if(location.compareTo(new BigDecimal(1* Unit.Km.value()))>0)
			return (location.divide(new BigDecimal(1.0*Unit.Km.value()), Configuration.mc)).setScale(2, RoundingMode.CEILING).toEngineeringString() + " Km";
		else
			return location.toEngineeringString();
	}

}
