package assignment.parking.simulator;

/**
 * @author Tianqi Wen (tianqiw)
 */
public class ParkedCar {
	private String make;
	private String model;
	private String color;
	private int licenseNum;
	private int minutes;

	public ParkedCar() {
		minutes = 0;
	}

	public ParkedCar(String make, String model, String color,
			int licenseNum) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.licenseNum = licenseNum;
		this.minutes = 0;
	}
	
	// getters and setters

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(int licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}
