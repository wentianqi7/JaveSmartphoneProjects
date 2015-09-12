package assignment.parking.simulator;

/**
 * @author Tianqi Wen (tianqiw)
 */
public class Simulation {
	private final String POLICE_NAME = "Carnegie";
	private final String MAKE = "Buick";
	private final String MODEL = "Cascada";
	private final String COLOR = "RED";
	private final int BADGE_NUM = (int) (Math.random() * Integer.MAX_VALUE + 1);
	private final int LICENSE = (int) (Math.random() * Integer.MAX_VALUE + 1);

	public static void main(String[] args) {
		Simulation sim = new Simulation();

		// initialization, set parking meter time to 230 minutes
		PoliceOfficer police = new PoliceOfficer(sim.POLICE_NAME, sim.BADGE_NUM);
		ParkingMeter meter = new ParkingMeter(230);
		ParkedCar car = new ParkedCar(sim.MAKE, sim.MODEL, sim.COLOR,
				sim.LICENSE);

		// case 1: parking time < 230 min
		sim.singleSimulation(0, police, car, meter, 0);
		sim.singleSimulation(50, police, car, meter, 0);

		// case 2: parking time = 230 min
		sim.singleSimulation(230, police, car, meter, 0);

		// case 3: parking time > 230 min
		// 3.1: ticket under 1 hour
		sim.singleSimulation(231, police, car, meter, 25);

		// 3.2: ticket equal 1 hour
		sim.singleSimulation(290, police, car, meter, 25);

		// 3.3 ticket more than 1 hour
		sim.singleSimulation(291, police, car, meter, 35);
		sim.singleSimulation(1000, police, car, meter, 145);
	}

	/**
	 * assert the amount of fine and print out simulation info
	 * 
	 * @param minutes - number of minutes the car parked
	 * @param police - the police exams the car
	 * @param car - the parked car
	 * @param meter - the meter purchased by the car
	 * @param answer - correct amount of fine
	 */
	private void singleSimulation(int minutes, PoliceOfficer police,
			ParkedCar car, ParkingMeter meter, int answer) {
		car.setMinutes(minutes);
		StringBuilder sb = new StringBuilder();
		sb.append("Simulation: Parking time is ");
		sb.append(minutes);
		sb.append(" minutes\n");
		if (police.examCar(car, meter)) {
			ParkingTicket ticket = police.issueTicket(car, meter);
			assert ticket.getFine() == answer : "Wrong answer: " + ticket.getFine();
			sb.append("A ticket is issued by the police:\n");
			sb.append("Fine: ");
			sb.append(ticket.getFine());
			sb.append("\nPolice Name: ");
			sb.append(police.getName());
			sb.append("\nPolice Badge Number: ");
			sb.append(police.getBadgeNum());
			sb.append("\nMake: ");
			sb.append(car.getMake());
			sb.append("\nModel: ");
			sb.append(car.getModel());
			sb.append("\nColor: ");
			sb.append(car.getColor());
			sb.append("\nLicense Number: ");
			sb.append(car.getLicenseNum());
			sb.append("\n\n");
			System.out.println(sb.toString());
		} else {
			assert answer == 0 : "Wrong answer";
			System.out
					.println("The car is within the parking time purchased.\n");
		}
	}
}
