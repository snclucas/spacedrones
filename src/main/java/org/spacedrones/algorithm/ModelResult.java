package org.spacedrones.algorithm;

import java.util.HashMap;

public class ModelResult {
	
	private HashMap<String, Double> result;
	
	
	public ModelResult() {
		super();	
		this.result = new HashMap<String, Double>();
	}
	
	public ModelResult(double[] resultArr) {
		super();
		this.result = new HashMap<String, Double>();
		
		int cnt = 0;
		for(Double val: resultArr)
			addResult("result"+cnt++, val);
	}
	
	public ModelResult(String[] resultTags, double[] resultArr) {
		super();
			
		this.result = new HashMap<String, Double>();
		
		//Only add as many as there are tags
		for(int cnt = 0; cnt < resultTags.length; cnt++)
			addResult(resultTags[cnt], resultArr[cnt]);
	}

	
	
	
	public void addResult(String tag, double result) {
		this.result.put(tag, result);
	}


	public HashMap<String, Double> getResults() {
		return result;
	}
	
	public double getResult(String tag) {
		if(result.containsKey(tag))
			return result.get(tag);
		else 
			return -99;
	}
	

}
