package org.spacedrones.algorithm;

public class SimpleRadioFrequencyPropagationModel extends AbstractModel {

	public static double RANGE_CALC = 1;
	public static double POWER_CALC = 2;
	
	
	public SimpleRadioFrequencyPropagationModel(String name) {
		super(name);
	}
	
	
	@Override
	public ModelResult getResult(ModelInputs inputs) {
		return runModel(inputs);
	}




	private ModelResult runModel(ModelInputs inputs) {
		
		double calcType = inputs.getInput("CALC_TYPE");
		double setPower = inputs.getInput("POWER");
		double noise = inputs.getInput("NOISE"); //0 - 10
		double efficiency = inputs.getInput("EFFICIENCY"); //0 - 1
		double recieveThreshold =  inputs.getInput("RECIEVE_THRESHOLD");
		//double maxPower = inputs.getInput("MAX_POWER");
		
		double suppliedRange = inputs.getInput("SUPPLIED_RANGE");
		
		double powerAtAntenna = (setPower * efficiency);
		
		boolean metCriteria = false;
		
		ModelResult result = new ModelResult();
		result.addResult("CALC_TYPE", calcType);
		
		if(calcType == RANGE_CALC) {
			double range = Math.pow(powerAtAntenna/recieveThreshold, 0.5);
			//Apply noise
			range = range * (1-noise/10.0);
			metCriteria = (range >= suppliedRange);
			result.addResult("CALCULATED_RANGE", range);
		}
		else {
			double power = powerAtAntenna/Math.pow(suppliedRange,2);
			metCriteria = (power >= recieveThreshold);
			result.addResult("CALCULATED_POWER", power);
		}
		
		result.addResult("MET_CRITERIA", (metCriteria)? 1.0:0.0);
		return result;
	}
	
	
	
	
	
	

}
