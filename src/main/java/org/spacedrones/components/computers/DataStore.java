package org.spacedrones.components.computers;

import java.util.*;

import org.spacedrones.components.*;

public interface DataStore {
	void saveData(DataRecord data);
  Optional<DataRecord> getData(String id, Class type);
	Map<String, DataRecord> getData(Class type);
	void saveData(DataRecord ... data);
	void saveData(List<DataRecord> data);
}
