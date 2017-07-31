package org.spacedrones;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;

import org.spacedrones.components.sensors.LocalSensorResponseMediator;
import org.spacedrones.components.sensors.SensorResponseMediator;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.data.LocalEnvironmentDataProvider;
import org.spacedrones.data.LocalMaterialDataProvider;
import org.spacedrones.data.LocalPhysicsDataProvider;
import org.spacedrones.data.LocalSpacecraftDataProvider;
import org.spacedrones.data.MaterialDataProvider;
import org.spacedrones.data.PhysicsDataProvider;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.dataprovider.LocalUniverseLocationDataProvider;
import org.spacedrones.universe.dataprovider.LocalUniverseSpacecraftDataProvider;
import org.spacedrones.universe.dataprovider.UniverseLocationDataProvider;
import org.spacedrones.universe.dataprovider.UniverseSpacecraftLocationDataProvider;

public class Configuration {

	public static int precision = 20;
	public static int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
	public static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	public static double distanceForEnvironmentData = 100.0 * Unit.Ly.value();

	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

	
	public static UniverseLocationDataProvider getUniverseLocationDataProvider() {
		return new LocalUniverseLocationDataProvider();
	}
	
	
	public static UniverseSpacecraftLocationDataProvider getUniverseSpacecraftLocationDataProvider() {
		return new LocalUniverseSpacecraftDataProvider();
	}
	
	
	public static EnvironmentDataProvider getEnvironmentDataProvider() {
		return new LocalEnvironmentDataProvider();
	}
	
	public static SensorResponseMediator getSensorResponseMediator() {
		return new LocalSensorResponseMediator();
	}
	
	
	public static SpacecraftDataProvider getSpacecraftDataProvider() {
		return new LocalSpacecraftDataProvider();
	}
	
	
	public static PhysicsDataProvider getPhysicsDataProvider() {
		return new LocalPhysicsDataProvider();
	}
	

	public static MaterialDataProvider getMaterialDataProvider() {
		return new LocalMaterialDataProvider();
	}

}
