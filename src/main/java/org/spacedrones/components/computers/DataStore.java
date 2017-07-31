package org.spacedrones.components.computers;

import java.util.List;
import java.util.Map;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;

public interface DataStore {
	TypeInfo categoryID = new TypeInfo("DataStore");
	
	void saveData(DataRecord data);
	DataRecord getData(String id, TypeInfo typeInfo);
	Map<String, DataRecord> getData(TypeInfo typeInfo);
	
	Map<String,DataRecord> getData(TypeInfo category, TypeInfo... subType);
	
	void saveData(Identifiable ... data);
	void saveData(List<? extends Identifiable> data);
}
