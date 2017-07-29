package org.spacedrones.components.computers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.spacedrones.components.TypeInfo;

public class Archive  extends HashMap<String, DataRecord> {
	private static final long serialVersionUID = 925935940538264787L;
	
	
//	public HashMap<String, DataRecord> filter(TypeInfo type) {
//		return entrySet().stream()
//				.filter(map -> type.equals(map.getValue().getRecordType()))
//				.map(map -> map.getValue())
//				.collect(Collectors.toMap(p -> ()p.getKey(), p -> p.getValue()));
//	}
//	
	
	public <K, V> Map<String, DataRecord> filterByType(TypeInfo type) {
		
		Predicate<DataRecord> predicate = value -> value.equals(type);
		
		return (Map<String, DataRecord>) entrySet().stream()
				.filter(entry -> predicate.test(entry.getValue()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}
	
	
}
