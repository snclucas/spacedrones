package org.spacedrones.components.computers;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;

public class SpacecraftData implements ArchivableData {
	private TypeInfo type = new TypeInfo("SpacecraftData");
	private final String dataName;
	private final String data;
	private final String ident;
	
	public SpacecraftData(String dataName, String data) {
		super();
		this.dataName = dataName;
		this.data = data;
		this.ident = Configuration.getUUID();
	}
	

	public String getDataName() {
		return dataName;
	}

	@Override
	public String getData() {
		return data;
	}


	@Override
	public TypeInfo getType() {
		return type;
	}


	@Override
	public TypeInfo getCategory() {
		return category;
	}


	@Override
	public String getName() {
		return this.dataName;
	}


	@Override
	public String getIdent() {
		return this.ident;
	}


	@Override
	public String describe() {
		return "Data record to hold spacecraft data.";
	}


}
