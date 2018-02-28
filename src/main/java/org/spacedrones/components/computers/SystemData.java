package org.spacedrones.components.computers;

public class SystemData implements Savable {

	private final String tag;
	private final String data;

	public SystemData(String tag, String data) {
		super();
		this.tag = tag;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	@Override
	public String getId() {
		return tag;
	}
}
