package org.spacedrones.components.comms;

import org.spacedrones.algorithm.Model;
import org.spacedrones.algorithm.ModelInputs;
import org.spacedrones.algorithm.ModelResult;
import org.spacedrones.algorithm.SimpleRadioFrequencyPropagationModel;
import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.physics.Physics;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;

import java.util.List;

public abstract class AbstractCommunicationComponent extends AbstractBusComponent implements CommunicationComponent {

	private static double MAX_POWER_LEVEL = 100;

	private double range;
	protected double powerLevel;
	private double efficiency = 0.3;
	private double deviceNoiseLevel = Physics.dBm2W(-80); // GJ/s or GW

	private List<String> recievedMessages;
	private List<String> broadcastMessages;

	private int broadcastMessageQueueSize = 10;

	private Model propagationModel;


	AbstractCommunicationComponent(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification);
		this.propagationModel = propagationModel;
	}

	@Override
	public void broadcast(String message, int recipient) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasRecievedMessages() {
		return recievedMessages.size() > 0;
	}

	@Override
	public List<String> getMessages() {
		return recievedMessages;
	}

	@Override
	public boolean addMessageToQueue(String message) {
		if(broadcastMessageQueueSize != broadcastMessages.size()) {
			broadcastMessages.add(message);
			return true;
		}
		else {
			return false;
		}
	}


	public double getPowerLevel() {
		return powerLevel;
	}


	public void setPowerLevel(double powerLevel) {
		this.powerLevel = powerLevel;
	}


	public double getRange() {
		return getRange(this.powerLevel);
	}


	public double getRange(double powerLevel) {
		ModelInputs inputs = new ModelInputs();
		inputs.addInput("CALC_TYPE", SimpleRadioFrequencyPropagationModel.RANGE_CALC);
		inputs.addInput("POWER", getMaximumPower(Unit.MW) * (powerLevel/100.00));
		inputs.addInput("NOISE", 0);
		inputs.addInput("EFFICIENCY", this.efficiency);
		inputs.addInput("RECIEVE_THRESHOLD", this.deviceNoiseLevel); //GJ/s
		inputs.addInput("MAX_POWER", getMaximumPower(Unit.MW));

		ModelResult result = propagationModel.getResult(inputs);
		return result.getResult("CALCULATED_RANGE");
	}



	@Override
	public boolean requestRange(double powerLevel) {
		//XXX FIX!
		double calcedRange = getRange(powerLevel);
		return calcedRange >= range;
	}


	@Override
	public double getMaximumRange() {
		return getRange(MAX_POWER_LEVEL);
	}


	public double getEfficiency() {
		return efficiency;
	}


	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}


	@Override
	public void setDeviceNoiseLevel(double recieveThreshold) {
		this.deviceNoiseLevel = recieveThreshold;
	}

	@Override
	public double getDeviceNoiseLevel() {
		return this.deviceNoiseLevel;
	}


	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by comm device: " + name() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage);
	}

	@Override
	public void tick(double dt) {
	}

}
