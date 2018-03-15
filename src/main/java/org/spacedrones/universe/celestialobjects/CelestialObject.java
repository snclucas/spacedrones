package org.spacedrones.universe.celestialobjects;

import org.spacedrones.Signature;
import org.spacedrones.components.Taxonomic;

public interface CelestialObject extends Taxonomic, Signature {
	SensorSignalResponseProfile getSensorSignalResponse();
}
