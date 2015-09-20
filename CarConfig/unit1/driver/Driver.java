package driver;

import model.Automotive;
import util.Util;

/**
 * Driver class used for testing
 * @author Tianqi Wen (tianqiw)
 */
public class Driver {
	private static final String TEXT_FILE = "configuration.txt";
	private static final String SERIALIZED_FILE = "Resources/automotive.dat";
	private static final String[] NEW_COLOR = {
		"Fort Knox Gold Clearcoat Metallic:100",
		"Liquid Grey Clearcoat Metallic:90"
	};
	
	public static void main(String[] args) {
		Util util = new Util();
		Automotive FordZTW = util.buildAutoObject(TEXT_FILE, new Automotive());
		System.out.println(FordZTW.toString());
		
		util.writeToSerializedFile(SERIALIZED_FILE, FordZTW);
		Automotive newFordZTW = util.readSerializedFile(SERIALIZED_FILE);
		System.out.println(newFordZTW.toString());
		
		// test delete a single optionset
		newFordZTW.deleteOpset("Power Moonroof");
		System.out.println(newFordZTW.toString());
		
		// test update the optionset
		newFordZTW.updateOptionSet("Color", NEW_COLOR);
		System.out.println(newFordZTW.toString());
		
		// test delete all the optionsets
		newFordZTW.deleteOpset("Color");
		newFordZTW.deleteOpset("Side Impact Air Bags");
		newFordZTW.deleteOpset("Transmission");
		newFordZTW.deleteOpset("Brakes/Traction Control");
		System.out.println(newFordZTW.toString());
	}
}
