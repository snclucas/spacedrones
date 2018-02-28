package org.spacedrones.components.computers;

import org.spacedrones.Configuration;
import org.spacedrones.components.Identifiable;

public class SystemData extends DataRecord {

	private final String tag;
	private final String data;

	public SystemData(String tag, String data) {
		super();
		this.tag = tag;
		this.data = data;
	}



}
