package org.spacedrones.structures.hulls;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.materials.Material;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;

public abstract class AbstractHull extends AbstractBusComponent implements Hull {

	protected HullSpecification hullSpecification;
	protected TypeInfo hullType;
	
	protected double usedVolume;
	
	private double hullSkinVolume;
	
	
	
	public AbstractHull(String name, HullSpecification hullSpecification, TypeInfo hullType) {
		super(name, hullSpecification.getBusResourceSpecification());
		this.hullSpecification = hullSpecification;
		this.hullType = hullType;
		
		busResourceSpecification.setVolume(calculateVolume(hullSpecification.getWidth(), hullSpecification.getLength(), hullSpecification.getHeight()));
		this.hullSkinVolume = calculateVolumeOfHullOuter(hullSpecification.getWidth(), hullSpecification.getLength(), hullSpecification.getHeight(), hullSpecification.getThickness());
	}
	
	
	@Override
	public TypeInfo getCategory() {
		return category;
	}
	
	
	private double calculateVolume(double width, double length, double height) {
		
		if(hullType == Hull.SPHEROID)
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
	public double getThickness() {
		return hullSpecification.getThickness();
	}


	@Override
	public double getMass() {	
		return this.hullSkinVolume * getMaterial().getDensity() * HULL_VOLUME_FRACTION;
	}


	public Material getMaterial() {
		return hullSpecification.getMaterial();
	}
	

	public double getLength() {
		return hullSpecification.getLength();
	}


	public double getWidth() {
		return hullSpecification.getWidth();
	}


	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}


	@Override
	public double getVolume() {
		return super.getVolume();
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
	public void tick() {
		System.out.println(this.getName() + " tick!");
	}

}
