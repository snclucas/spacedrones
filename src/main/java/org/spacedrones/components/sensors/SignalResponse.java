package org.spacedrones.components.sensors;

public class SignalResponse {
	
	private final double signalStrength;
	private final double signalDispersion;
	
	public SignalResponse(double signalStrength, double signalDispersion) {
		super();
		this.signalStrength = signalStrength;
		this.signalDispersion = signalDispersion;
	}

	public double getSignalStrength() {
		return signalStrength;
	}

	public double getSignalDispersion() {
		return signalDispersion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(signalDispersion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(signalStrength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignalResponse other = (SignalResponse) obj;
		if (Double.doubleToLongBits(signalDispersion) != Double
				.doubleToLongBits(other.signalDispersion))
			return false;
    return Double.doubleToLongBits(signalStrength) == Double
            .doubleToLongBits(other.signalStrength);
  }
	
}
