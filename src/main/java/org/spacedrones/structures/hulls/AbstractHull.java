package org.spacedrones.structures.hulls;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.materials.Material;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;

public abstract class AbstractHull extends AbstractBusComponent implements Hull {

	final HullSpecification hullSpecification;
	final Hull.Type hullType;
	
	double usedVolume;
	
	double hullSkinVolume;

	
	public AbstractHull(String name, HullSpecification hullSpecification, Hull.Type hullType) {
		super(name, hullSpecification.getBusResourceSpecification());
		this.hullSpecification = hullSpecification;
		this.hullType = hullType;

    hullSpecification.getBusResourceSpecification().setVolume(calculateVolume(hullSpecification.getWidth(Unit.m), hullSpecification.getLength(Unit.m), hullSpecification.getHeight(Unit.m)));
		this.hullSkinVolume = calculateVolumeOfHullOuter(hullSpecification.getWidth(Unit.m), hullSpecification.getLength(Unit.m), hullSpecification.getHeight(Unit.m), hullSpecification.getThickness(Unit.m));
	}
	
	
	@Override
	public TypeInfo category() {
		return category;
	}

	@Override
	public TypeInfo type() {
		return type;
	}
	
	private double calculateVolume(double width, double length, double height) {
		if(hullType == Hull.Type.SPHEROID)
			return (4.0*Math.PI/3.0)*width*width*length;
		else 
			return width*length*height;
	}
	
	
	private double calculateVolumeOfHullOuter(double width, double length, double height, double thickness) {
		double innerVolume = calculateVolume(width, length, height);
		double outerVolume = calculateVolume(width+2*thickness, length+2*thickness, height+2*thickness);
		return outerVolume - innerVolume;	
	}
	
	@Override
	public double getThickness(Unit unit) {
		return hullSpecification.getThickness(unit);
	}


	@Override
	public double getMass(Unit unit) {
		return this.hullSkinVolume * getMaterial().getDensity() * HULL_VOLUME_FRACTION / unit.value();
	}


	public Material getMaterial() {
		return hullSpecification.getMaterial();
	}
	

	public double getLength(Unit unit) {
		return hullSpecification.getLength(unit);
	}


	public double getWidth(Unit unit) {
		return hullSpecification.getWidth(unit);
	}

  public double getHeight(Unit unit) {
    return hullSpecification.getHeight(unit);
  }

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	
	//Delegate methods to get the hull resistances
	
	public double getImpactResistance() {
		return hullSpecification.getMaterial().getImpactResistance() * getImpactResistanceModifier();
	}

	public double getEMResistance() {
		return hullSpecification.getMaterial().getEMResistance() * getEmResistanceModifier();
	}

	public double getRadiationResistance() {
		return hullSpecification.getMaterial().getRadiationResistance() * getRadiationResistanceModifier();
	}

	public double getThermalResistance() {
		return hullSpecification.getMaterial().getThermalResistance() * getThermalResistanceModifier();
	}
	
	
	public double getImpactResistanceModifier() {
		return hullSpecification.getImpactResistanceModifier();
	}


	public double getEmResistanceModifier() {
		return hullSpecification.getEmResistanceModifier();
	}


	public double getThermalResistanceModifier() {
		return hullSpecification.getThermalResistanceModifier();
	}


	public double getRadiationResistanceModifier() {
		return hullSpecification.getRadiationResistanceModifier();
	}
	
	@Override
	public void tick(double dt) {
		System.out.println(this.getName() + " tick!");
	}

}
