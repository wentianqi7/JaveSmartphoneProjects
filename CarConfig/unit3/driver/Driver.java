package driver;

import adapter.BuildAuto;

/**
 * Driver class used for testing
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Driver extends Thread {
	private static final String[] FILENAME = { "configuration.txt",
			"configuration2.txt" };
	private static final String[] MODEL_NAME = { "Focus Wagon ZTW",
			"Test Model" };
	private static final String[] OPTIONSET = { "Color", "Transmission",
			"Brakes/Traction Control", "Side Impact Air Bags", "Power Moonroof" };
	private BuildAuto build;

	public static void main(String[] args) {
		Driver dr = new Driver();
		dr.build = new BuildAuto();
		dr.build.buildAuto(FILENAME[0]);
		dr.build.buildAuto(FILENAME[1]);
		dr.start();
	}

	private void waitTime(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Interrupted.");
		}
	}

	public void run() {
		// synchronized update and delete - no error
		// either not found option or update first and then delete
		System.out.println("\nTest 1: synchronized update");
		waitTime(10);
		build.synDeleteOption(MODEL_NAME[1], OPTIONSET[1], "automatic");
		build.synUpdateOptionPrice(MODEL_NAME[1], OPTIONSET[1], "automatic",
				200);

		waitTime(1000);

		// delete the same option in both thread
		// the program will crash when the option to be updated is already
		// deleted if not synchronized
		System.out.println("\nTest 2: non synchronized update");
		waitTime(10);
		build.nonSynDeleteOption(MODEL_NAME[1], OPTIONSET[1], "manual");
		build.nonSynUpdateOptionPrice(MODEL_NAME[1], OPTIONSET[1], "manual",
				200);

		waitTime(1000);

		// synchronized delete on same option - no error
		System.out.println("\nTest 3: synchronized delete");
		waitTime(10);
		build.synDeleteOption(MODEL_NAME[0], OPTIONSET[1], "automatic");
		build.synDeleteOption(MODEL_NAME[0], OPTIONSET[1], "automatic");

		waitTime(1000);

		// If both threads fetch the option to delete first, the program will
		// crash if the later thread tries to delete the option
		System.out.println("\nTest 4: non synchronized delete");
		waitTime(10);
		build.nonSynDeleteOption(MODEL_NAME[0], OPTIONSET[1], "manual");
		build.nonSynDeleteOption(MODEL_NAME[0], OPTIONSET[1], "manual");
	}
}
