package org.spacedrones.components.computers;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;

public class SpacecraftData implements ArchivableData {
	private TypeInfo type = new TypeInfo("SpacecraftData");
	private final String dataName;
	private final String data;
	private final String id;
	
	public SpacecraftData(String dataName, String data) {
		super();
		this.dataName = dataName;
		this.data = data;
		this.id = Configuration.getUUID();
	}

	public String getDataName() {
		return dataName;
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public String getName() {
		return this.dataName;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String describe() {
		return "Data record to hold spacecraft data.";
	}

}
