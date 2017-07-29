package org.spacedrones.data;

import org.spacedrones.components.TypeInfo;

public interface SpacecraftComponentDataProvider {
	SpacecraftComponentData getComponentParameters(TypeInfo componentTag);
}
