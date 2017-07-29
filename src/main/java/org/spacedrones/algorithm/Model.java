package org.spacedrones.algorithm;

public interface Model {
	
	public void setName(String name);

    public String getName();
    
    public ModelResult getResult(ModelInputs inputs);
    
    
}
