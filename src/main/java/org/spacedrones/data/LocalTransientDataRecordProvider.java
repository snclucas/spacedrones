package org.spacedrones.data;

import java.util.*;
import java.util.Map.Entry;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.DataRecord;

public class LocalTransientDataRecordProvider implements DataRecordProvider {

	private Map<String, DataRecord> dataRecords;


	public LocalTransientDataRecordProvider() {
		this.dataRecords = new HashMap<>();
	}

	@Override
	public Optional<DataRecord> getDataRecordByID(String id) {
		DataRecord returnedRecord = dataRecords.get(id);
		if(returnedRecord == null)
			return Optional.empty();
		return null;
	}

	@Override
	public Optional<DataRecord> addDataRecord(DataRecord dataRecord) {
		if(!dataRecords.containsValue(dataRecord))
			return Optional.of(dataRecords.put(dataRecord.getRecordID(), dataRecord));
		return getDataRecordByID(dataRecord.getRecordID());
	}

	@Override
	public Optional<DataRecord> overwriteDataRecordWithId(String id, DataRecord dataRecord) {
		if(dataRecords.containsValue(dataRecord))
      return Optional.of(dataRecords.put(dataRecord.getRecordID(), dataRecord));
		return Optional.empty();
	}

	@Override
	public List<DataRecord> getDataRecordsByType(Class<? extends SpacecraftBusComponent> type) {
		List<DataRecord> records = new ArrayList<>();
    for (Entry<String, DataRecord> stringDataRecordEntry : dataRecords.entrySet()) {
      DataRecord record = stringDataRecordEntry.getValue();
      if (type == record.getType())
        records.add(record);
    }
		return records;
	}

	@Override
	public Optional<DataRecord> deleteDataRecordByID(String id) {
		Optional<DataRecord> recordToDelete = getDataRecordByID(id);
		if(recordToDelete.isPresent() && recordToDelete.get().hasData())
			return Optional.of(dataRecords.remove(id));
		else
			return Optional.empty();
	}

}
