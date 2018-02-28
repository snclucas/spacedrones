package org.spacedrones.components.computers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DataStore {
	void saveData(DataRecord data);
  Optional<DataRecord> getData(String id, String type);
	Map<String, DataRecord> getData(String type);
	void saveData(DataRecord ... data);
	void saveData(List<DataRecord> data);
}
