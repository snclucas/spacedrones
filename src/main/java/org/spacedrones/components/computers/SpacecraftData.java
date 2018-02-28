package org.spacedrones.components.computers;

import org.spacedrones.Configuration;

public class SpacecraftData implements ArchivableData {
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
	public String name() {
		return this.dataName;
	}

	@Override
	public String id() {
		return this.id;
	}

	@Override
	public String description() {
		return "Data record to hold spacecraft data.";
	}

}
