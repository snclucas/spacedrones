package org.spacedrones.components.computers;

import org.spacedrones.spacecraft.BusComponentSpecification;

import java.util.*;


public class BasicDataStorageUnit extends AbstractDataStorageUnit  {

	BasicDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	private final Map<String, Archive> dataArchives = new HashMap<>();

	@Override
	public void saveData(DataRecord data) {
		if(dataArchives.containsKey(data.getType())) {
			Archive archive = dataArchives.get(data.getType());
			archive.put(data.getRecordID(), data);
		}
		else {
			Archive archive = new Archive();
			archive.put(data.getRecordID(), data);
			dataArchives.put(data.getType(), archive);
		}
	}

	@Override
	public Optional<DataRecord> getData(String id, String type) {
		if(dataArchives.containsKey(type)) {
			Archive archive = dataArchives.get(type);
			return Optional.of(archive.get(id));
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Map<String,DataRecord> getData(String type) {
		Map<String,DataRecord> results = new HashMap<>();
		if(dataArchives.containsKey(type)) {
			for(Map.Entry<String,DataRecord>  a : dataArchives.get(type).entrySet()) {
        results.put(a.getKey(), a.getValue());
			}
		}
		return results;
	}

	@Override
	public String description() {
		return toString();
	}

	@Override
	public String toString() {
		return "BasicDataStorageUnit";
	}

	@Override
	public void saveData(DataRecord ... data) {
		List<DataRecord> list = new ArrayList<>(Arrays.asList(data));
		saveData(list);
	}


	@Override
	public void saveData(List<DataRecord> data) {
		for(DataRecord d : data) {
			saveData(d);
		}
	}

}

