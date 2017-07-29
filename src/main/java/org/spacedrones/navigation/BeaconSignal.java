package org.spacedrones.navigation;

import org.spacedrones.universe.structures.SubspaceBeacon;

public class BeaconSignal {
	
	private SubspaceBeacon beacon;
	private double signalDispersion;
	
	
	public BeaconSignal(SubspaceBeacon beacon, double signalDispersion) {
		super();
		this.beacon = beacon;
		this.signalDispersion = signalDispersion;
	}


	public SubspaceBeacon getBeacon() {
		return beacon;
	}


	public double getSignalDispersion() {
		return signalDispersion;
	}


}
