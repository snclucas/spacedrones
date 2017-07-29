package org.spacedrones.data;

import java.util.List;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.DataRecord;

public interface DataRecordProvider {
	DataRecord getDataRecordByID(String id);
	DataRecord addDataRecord(DataRecord dataRecord);
	DataRecord overwriteDataRecordWithId(String id, DataRecord dataRecord);
	List<DataRecord> getDataRecordsByType(TypeInfo type);
	DataRecord deleteDataRecordByID(String id);
}
