package org.spacedrones.components.computers;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.comms.Status;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

import java.util.*;


public class BasicDataStorageUnit extends AbstractDataStorageUnit  {

	BasicDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	private final Map<String, Archive> dataArchives = new HashMap<>();

	@Override
	public void saveData(DataRecord data) {
		if(dataArchives.containsKey(data.getType().getSimpleName())) {
			Archive archive = dataArchives.get(data.getType().getSimpleName());
			archive.put(data.getRecordID(), data);
		}
		else {
			Archive archive = new Archive();
			archive.put(data.getRecordID(), data);
			dataArchives.put(data.getType().getSimpleName(), archive);
		}
	}

	@Override
	public Optional<DataRecord> getData(String id, Class type) {
		if(dataArchives.containsKey(type.getSimpleName())) {
			Archive archive = dataArchives.get(type.getSimpleName());
			return Optional.of(archive.get(id));
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Map<String,DataRecord> getData(Class type) {
		Map<String,DataRecord> results = new HashMap<>();
		if(dataArchives.containsKey(type.getSimpleName())) {
			for(Map.Entry<String,DataRecord>  a : dataArchives.get(type.getSimpleName()).entrySet()) {
        results.put(a.getKey(), a.getValue());
			}

		}
		return results;
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + name() +"] OK", Status.OK);
		return systemStatus;
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(name() + " online.", Status.OK);
		return systemStatus;
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
	public void saveData(DataRecord... data) {
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

