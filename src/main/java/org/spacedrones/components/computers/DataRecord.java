package org.spacedrones.components.computers;


public class DataRecord<T extends Savable> {

	private final String id;
	private final T data;
	private String type;


	public DataRecord(String id, String type, T data) {
		this.id = id;
		this.type = type;
		this.data = data;
	}

  public String getType() {
		return type;
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
