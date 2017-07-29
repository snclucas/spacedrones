package org.spacedrones.structures.hulls;

import org.spacedrones.materials.Material;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class HullSpecification {
	
	protected String name;
	protected BusComponentSpecification busResourceSpecification; 
	protected double thickness;
	protected Material material;
	
	protected double impactResistanceModifier;
	protected double emResistanceModifier;
	protected double thermalResistanceModifier;
	protected double radiationResistanceModifier;
	
	
	public HullSpecification(String name,
			BusComponentSpecification busResourceSpecification, double thickness, Material material,
			double impactResistanceModifier, double emResistanceModifier,
			double thermalResistanceModifier, double radiationResistanceModifier) {
		super();
		this.name = name;
		this.busResourceSpecification = busResourceSpecification;
		this.thickness = thickness;
		this.material = material;
		this.impactResistanceModifier = impactResistanceModifier;
		this.emResistanceModifier = emResistanceModifier;
		this.thermalResistanceModifier = thermalResistanceModifier;
		this.radiationResistanceModifier = radiationResistanceModifier;
	}

	
	public HullSpecification(String name,
			BusComponentSpecification busResourceSpecification, double thickness, Material material) {
		super();
		this.name = name;
		this.busResourceSpecification = busResourceSpecification;
		this.thickness = thickness;
		this.material = material;
		this.impactResistanceModifier = 1.0;
		this.emResistanceModifier = 1.0;
		this.thermalResistanceModifier = 1.0;
		this.radiationResistanceModifier = 1.0;
	}

	
	public String getName() {
		return name;
	}


	public BusComponentSpecification getBusResourceSpecification() {
		return busResourceSpecification;
	}


	public double getLength() {
		return busResourceSpecification.getLength();
	}


	public double getWidth() {
		return busResourceSpecification.getWidth();
	}
	
	
	public double getHeight() {
		return busResourceSpecification.getHeight();
	}


	public double getThickness() {
		return thickness;
	}


	public Material getMaterial() {
		return material;
	}


	public double getImpactResistanceModifier() {
		return impactResistanceModifier;
	}


	public double getEmResistanceModifier() {
		return emResistanceModifier;
	}


	public double getThermalResistanceModifier() {
		return thermalResistanceModifier;
	}


	public double getRadiationResistanceModifier() {
		return radiationResistanceModifier;
	}
	
	

}
