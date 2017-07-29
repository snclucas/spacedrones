package org.spacedrones.universe;

import java.math.BigDecimal;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;
import org.spacedrones.utils.Utils;

public abstract class AbstractLocation implements Location {
	
	protected String name;
	protected String id;
	protected Coordinates coordinates;	
	
	protected final String ident;
	
	public AbstractLocation(String name, Coordinates coordinates) {
		this.id = Configuration.getUUID();
		this.name = name;
		this.coordinates = coordinates;
		this.ident = Configuration.getUUID();
	}
	
	
	public AbstractLocation(String name, Coordinates coordinates, Location relativeTo) {
		this(name, coordinates.add(relativeTo.getCoordinates()));
	}
	
	
	public AbstractLocation(String name, BigDecimal[] coordComponents) {
		this(name, new Coordinates(coordComponents));
	}
	
	
	@Override
	public String getIdent() {
		return ident;
	}
	
	
	@Override
	public TypeInfo getCategory() {
		return categoryID;
	}
	

	@Override
	public String getName() {
		return name;
	}
	
	
	@Override
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	
	@Override
	public BigDecimal getCoordinate(int index) {
		return this.coordinates.get(index);
	}


	@Override
	public BigDecimal distanceToLocation(Location location, Unit unit)
	{
		return Utils.distanceToLocation(this.getCoordinates(), location.getCoordinates(), unit);
	}


	@Override
	public NavigationVector vectorToLocation(Location location, boolean normalized) {
		return Utils.vectorToLocation(this.getCoordinates(), location.getCoordinates(), normalized);
	}

	
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		AbstractLocation other = (AbstractLocation) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
	
	
	
	

}
