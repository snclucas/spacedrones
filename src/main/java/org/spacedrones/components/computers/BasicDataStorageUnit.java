package org.spacedrones.components.computers;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

import java.util.*;


public class BasicDataStorageUnit extends AbstractDataStorageUnit  {
	public static TypeInfo type = new TypeInfo("BasicDataStorageUnit");


	BasicDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	private final Map<TypeInfo, Archive> dataArchives = new HashMap<>();


	public TypeInfo type() {
		return type;
	}

	@Override
	public void saveData(DataRecord data) {
		if(dataArchives.containsKey(data.getRecordCategory())) {
			Archive archive = dataArchives.get(data.getRecordCategory());
			archive.put(data.getRecordID(), data);
		}
		else {
			Archive archive = new Archive();
			archive.put(data.getRecordID(), data);
			dataArchives.put(data.getRecordCategory(), archive);
		}
	}

	@Override
	public DataRecord getData(String id, TypeInfo typeInfo) {
		if(dataArchives.containsKey(typeInfo)) {
			Archive archive = dataArchives.get(typeInfo);
			return archive.get(id);
		}
		else {
			return new DataRecord();
		}
	}

	@Override
	public Map<String, DataRecord> getData(TypeInfo typeInfo) {
		if(dataArchives.containsKey(typeInfo)) {
			return dataArchives.get(typeInfo);
		}
		else {
			return new Archive();
		}
	}

	@Override
	public Map<String,DataRecord> getData(TypeInfo category, TypeInfo ... subTypes) {
		Map<String,DataRecord> results = new HashMap<>();
		if(dataArchives.containsKey(category)) {
			for(Map.Entry<String,DataRecord>  a : dataArchives.get(category).entrySet()) {
				if(Arrays.asList(subTypes).contains(a.getValue().getRecordType())) {
					results.put(a.getKey(), a.getValue());
				}
			}

		}
		return results;
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", Status.OK);
		return systemStatus;
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", Status.OK);
		return systemStatus;
	}

	@Override
	public String describe() {
		return toString();
	}

	@Override
	public String toString() {
		return "BasicDataStorageUnit";
	}

	@Override
	public void saveData(Identifiable... data) {
		List<Identifiable> list = new ArrayList<>(Arrays.asList(data));
		saveData(list);
	}


	@Override
	public void saveData(List<? extends Identifiable> data) {
		for(Identifiable d : data) {
			DataRecord record = new DataRecord(d.getId(), d);
			saveData(record);
		}
	}

}

