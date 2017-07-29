package org.spacedrones.algorithm;

import java.util.HashMap;

public class ModelInputs {
	
	private HashMap<String, Double> inputs;
	
	public ModelInputs() {
		super();	
		this.inputs = new HashMap<String, Double>();
	}
	
	public ModelInputs(double[] inputsArr) {
		super();
		this.inputs = new HashMap<String, Double>();
		
		int cnt = 0;
		for(Double val: inputsArr)
			addInput("input"+cnt++, val);
	}
	
	public ModelInputs(String[] inputTags, double[] inputsArr) {
		super();
			
		this.inputs = new HashMap<String, Double>();
		
		//Only add as many as there are tags
		for(int cnt = 0; cnt < inputTags.length; cnt++)
			addInput(inputTags[cnt], inputsArr[cnt]);
	}

	
	
	
	public void addInput(String tag, double result) {
		this.inputs.put(tag, result);
	}


	public HashMap<String, Double> getInputs() {
		return inputs;
	}
	
	public double getInput(String tag) {
		if(inputs.containsKey(tag))
			return inputs.get(tag);
		else 
			return 0;
	}

}
