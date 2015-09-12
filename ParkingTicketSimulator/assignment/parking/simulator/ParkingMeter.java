package assignment.parking.simulator;

/**
 * @author Tianqi Wen (tianqiw)
 */
public class ParkingMeter {
	private int meter;

	public ParkingMeter() {
		meter = 0;
	}
	
	public ParkingMeter(int meter) {
		this.meter = meter;
	}
	
	public int getMeter() {
		return meter;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}
	
}
