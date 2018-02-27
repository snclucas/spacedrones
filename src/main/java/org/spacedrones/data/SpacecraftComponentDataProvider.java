package org.spacedrones.data;

public interface SpacecraftComponentDataProvider {
	SpacecraftComponentData getComponentParameters(String componentTag);
}
