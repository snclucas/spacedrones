package org.spacedrones.components.computers;

import org.spacedrones.Configuration;
import org.spacedrones.components.Identifiable;

public class SystemData implements Identifiable {

	private final String tag;
	private final String data;

	public SystemData(String tag, String data) {
		super();
		this.tag = tag;
		this.data = data;
	}

	@Override
	public String name() {
		return "SystemData";
	}

	@Override
	public String id() {
		return Configuration.getUUID();
	}

	@Override
	public String description() {
		return "SystemData";
	}

}
