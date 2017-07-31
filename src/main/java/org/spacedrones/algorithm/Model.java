package org.spacedrones.algorithm;

public interface Model {
	
	void setName(String name);

    String getName();
    
    ModelResult getResult(ModelInputs inputs);
    
    
}
