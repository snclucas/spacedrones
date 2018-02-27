package org.spacedrones.components.computers;

import org.spacedrones.components.Identifiable;

public class DataRecord {
	
	private final String id;
	private final Object data;
	
	
	public DataRecord() {
		super();
		this.id = "";
		this.type = null;
		this.category = null;
		this.data = null;
	}
	
	public DataRecord(String id, Identifiable data) {
		super();
		this.id = id;
		this.type = new TypeInfo("Fix");//data.type();
		this.category = new TypeInfo("Fix");// data.category();
		this.data = data;
	}
	
	public TypeInfo getRecordType() {
		return type;
	}
	
	public TypeInfo getRecordCategory() {
		return category;
	}

	public String getRecordID() {
		return id;
	}

	public Object getData() {
		return data;
	}
	
	public boolean hasData() {
		return data != null;
	}

}
