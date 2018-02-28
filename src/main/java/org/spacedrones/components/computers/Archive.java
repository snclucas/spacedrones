package org.spacedrones.components.computers;

import org.spacedrones.components.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Archive  extends HashMap<String, DataRecord> {
	private static final long serialVersionUID = 925935940538264787L;


//	public HashMap<String, DataRecord> filter(TypeInfo type) {
//		return entrySet().stream()
//				.filter(map -> type.equals(map.getValue().getRecordType()))
//				.map(map -> map.getValue())
//				.collect(Collectors.toMap(p -> ()p.getKey(), p -> p.getValue()));
//	}
//

	public <K, V> Map<String, DataRecord> filterByType(Class<? extends SpacecraftBusComponent> type) {

		Predicate<DataRecord> predicate = value -> value.equals(type);

		return entrySet().stream()
				.filter(entry -> predicate.test(entry.getValue()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}


}
