package assignment.parking.simulator;

/**
 * @author Tianqi Wen (tianqiw)
 */
public class PoliceOfficer {
	private final float MIN_PER_HOUR = 60.f;
	private final int INIT_RATE = 25;
	private final int EXTRA_RATE = 10;
	private final int INIT_HOUR = 1;
	private String name;
	private int badgeNum;

	public PoliceOfficer() {

	}

	public PoliceOfficer(String name, int badgeNum) {
		this.name = name;
		this.badgeNum = badgeNum;
	}

	/**
	 * To examine a parked car and parking meter object and determine whether
	 * the car's time has expired
	 * 
	 * @param car
	 *            - the car to be examined
	 * @param meter
	 *            - the meter that the car purchased
	 * @return true if a ticket should be issued
	 */
	public boolean examCar(ParkedCar car, ParkingMeter meter) {
		return (car.getMinutes() - meter.getMeter()) > 0;
	}

	/**
	 * To issue a parking ticket if the car time has expired
	 * 
	 * @param car
	 * @param meter
	 * @return the ticket issued by this police
	 */
	public ParkingTicket issueTicket(ParkedCar car, ParkingMeter meter) {
		int minutes = car.getMinutes() - meter.getMeter();
		assert minutes > 0 : 
			"the police cannot issue a ticket if the car is within purchased hour";
		int fine = this.calculateFine(minutes);
		return new ParkingTicket(car.getMake(), car.getModel(), car.getColor(),
				car.getLicenseNum(), this.name, this.badgeNum, fine);
	}

	/**
	 * Calculate the fine according to exceeded minutes
	 * 
	 * @param minutes
	 * @return amount of fine
	 */
	private int calculateFine(int minutes) {
		if (minutes <= 0)
			return 0;
		int hour = (int) Math.ceil(minutes / MIN_PER_HOUR);
		return (hour > INIT_HOUR) ? ((hour - INIT_HOUR) * EXTRA_RATE + INIT_RATE
				* INIT_HOUR)
				: INIT_RATE * INIT_HOUR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBadgeNum() {
		return badgeNum;
	}

	public void setBadgeNum(int badgeNum) {
		this.badgeNum = badgeNum;
	}
}
