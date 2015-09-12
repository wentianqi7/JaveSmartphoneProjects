package assignment.parking.simulator;

/**
 * @author Tianqi Wen (tianqiw)
 */
public class ParkingTicket {
	private String make;
	private String model;
	private String color;
	private String name;
	private int licenseNum;
	private int badgeNum;
	private int fine;

	public ParkingTicket() {
		fine = 0;
	}

	public ParkingTicket(String make, String model, String color,
			int licenseNum, String name, int badgeNum, int fine) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.licenseNum = licenseNum;
		this.name = name;
		this.badgeNum = badgeNum;
		this.fine = fine;
	}

	// getters and setters

	public int getFine() {
		return fine;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public int getLicenseNum() {
		return licenseNum;
	}

	public String getName() {
		return name;
	}

	public int getBadgeNum() {
		return badgeNum;
	}
}
