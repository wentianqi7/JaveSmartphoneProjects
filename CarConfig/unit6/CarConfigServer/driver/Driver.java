package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;

/**
 * Test CUD of database
 * @author Tianqi Wen (tianqiw)
 *
 */
public class Driver {
	private static final String MODEL = "Focus Wagon ZTW";  
	private static final String CREATE_TABLE_FILE = "create.sql";
	private static final String DROP_TABLE_FILE = "drop.sql";
	
	public static void main(String[] args) {
		BuildAuto build = new BuildAuto();
		
		// create db tables
		System.out.println("(1) Init db");
		build.initDB(CREATE_TABLE_FILE, DROP_TABLE_FILE);
		build.showModelDB(MODEL);
		
		// When a new Automobile is added to the LinkedHashMap
		System.out.println("(2) Add auto");
		build.buildAuto("configuration", ".txt");
		build.showModelDB(MODEL);
		
		// When an Automobile is updated in the LinkedHashMap
		System.out.println("(3) Update price and name");
		build.updateOptionSetName(MODEL, "Color", "Color Choices");
		build.updateOptionPrice(MODEL, "Transmission", "automatic", 500.f);
		build.showModelDB(MODEL);
		
		// When an Automobile is deleted from the LinkedHashMap
		System.out.println("(4) delete model");
		build.deleteAuto("Focus Wagon ZTW");
		build.showModelDB(MODEL);
	}
}
