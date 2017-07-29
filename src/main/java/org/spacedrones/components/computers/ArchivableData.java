package org.spacedrones.components.computers;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;

public interface ArchivableData extends Identifiable {
	TypeInfo category = new TypeInfo("ArchivableData");
	String getData();
}
