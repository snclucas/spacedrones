package org.spacedrones.structures.hulls;

import org.spacedrones.materials.Material;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class HullSpecification {
	
	private final String name;
	private final BusComponentSpecification busResourceSpecification;
	private final double thickness;
	private final Material material;

	private final double impactResistanceModifier;
	private final double emResistanceModifier;
	private final double thermalResistanceModifier;
	private final double radiationResistanceModifier;
	
	
	HullSpecification(String name,
			BusComponentSpecification busResourceSpecification, double thickness, Material material,
			double impactResistanceModifier, double emResistanceModifier,
			double thermalResistanceModifier, double radiationResistanceModifier) {
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


	public double getLength(Unit unit) {
		return busResourceSpecification.getLength(unit);
	}


	public double getWidth(Unit unit) {
		return busResourceSpecification.getWidth(unit);
	}
	
	
	public double getHeight(Unit unit) {
		return busResourceSpecification.getHeight(unit);
	}


	public double getThickness(Unit unit) {
		return thickness / unit.value();
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
