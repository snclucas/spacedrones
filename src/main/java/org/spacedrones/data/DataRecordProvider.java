package org.spacedrones.data;

import java.util.*;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.DataRecord;

public interface DataRecordProvider {
  Optional<DataRecord> getDataRecordByID(String id);
  Optional<DataRecord> addDataRecord(DataRecord dataRecord);
  Optional<DataRecord> overwriteDataRecordWithId(String id, DataRecord dataRecord);
	List<DataRecord> getDataRecordsByType(Class<? extends SpacecraftBusComponent> type);
  Optional<DataRecord> deleteDataRecordByID(String id);
}
