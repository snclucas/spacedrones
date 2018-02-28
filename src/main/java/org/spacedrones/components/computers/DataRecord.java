package org.spacedrones.components.computers;


public class DataRecord<T> {

	private final String id;
	private final T data;
	private Class type;


	public DataRecord(String id, Class<T> type, T data) {
		super();
		this.id = id;
		this.type = type;
		this.data = data;
	}

  public Class getType() {
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
