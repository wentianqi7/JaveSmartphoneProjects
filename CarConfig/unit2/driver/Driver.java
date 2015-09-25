package driver;

import exception.AutoException;
import exception.CustomException;
import Adapter.BuildAuto;

/**
 * Driver class used for testing
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Driver {
	private static final String[] FILENAME = { "configuration.txt",
			"dup_opset.txt", "incomplete_opset.txt", "missing_option.txt",
			"missing_price.txt", "undeclared_opset.txt" };
	private static final String MODEL_NAME = "Focus Wagon ZTW";
	private static final String[] OPTIONSET = { "Color", "Transmission",
			"Brakes/Traction Control", "Side Impact Air Bags", "Power Moonroof" };
	private static final String SERIALIZED_FILENAME = "Resources/automotive.dat";
	private static final int OPSET_SIZE = 4;

	public static void main(String[] args) {
		// test create
		BuildAuto build = new BuildAuto();
		build.buildAuto(FILENAME[0]);
		build.printAuto(MODEL_NAME);

		// test set
		build.chooseOption(MODEL_NAME, OPTIONSET[0],
				"Liquid Grey Clearcoat Metallic");
		build.chooseOption(MODEL_NAME, OPTIONSET[1], "manual");
		build.chooseOption(MODEL_NAME, OPTIONSET[2], "Standard");
		build.chooseOption(MODEL_NAME, OPTIONSET[3], "present");
		build.chooseOption(MODEL_NAME, OPTIONSET[4], "not present");

		// test get single choice
		build.printChoice(MODEL_NAME, OPTIONSET[1]);

		// test get all choices
		build.printAllChoices(MODEL_NAME);

		// test get single choice price
		System.out.println(build.getChoicePrice(MODEL_NAME, OPTIONSET[3]));

		// test get total price
		System.out.println(build.getTotalPrice(MODEL_NAME));

		// test update
		build.updateOptionPrice(MODEL_NAME, OPTIONSET[1], "manual", -400);
		build.chooseOption(MODEL_NAME, OPTIONSET[1], "manual");
		System.out.println(build.getChoicePrice(MODEL_NAME, OPTIONSET[1]));

		build.updateOptionSetName(MODEL_NAME, OPTIONSET[4], "Roof");
		build.printChoice(MODEL_NAME, OPTIONSET[4]);
		build.printChoice(MODEL_NAME, "Roof");

		// test accessibility of fix method from FixAuto interface
		try {
			throw new AutoException(CustomException.UNDECLARED_OPTIONSET);
		} catch (AutoException ae) {
			build.fix(ae, MODEL_NAME);
		}

		// test exception handling with various models
		// test 1: Model1 with duplicate optionset name
		build.buildAuto(FILENAME[1]);
		build.printAuto("Model1");

		for (int j = 0; j < 3; j++) {
			String opset = "OptionSet" + j;
			String option = "Option" + (int) (Math.random() * 5);
			build.chooseOption("Model1", opset, option);
		}
		build.chooseOption("Model1", "OptionSet0-Rename", "Option3");

		build.printAllChoices("Model1");
		System.out.println(build.getTotalPrice("Model1"));

		/*
		 * test 2: Model2 with imcomplete option set
		 * test 3: Model3 with missing option
		 * test 4: Model4 with missing option price
		 * test 5: Model5 with undeclared option set
		 */
		for (int i = 2; i < FILENAME.length; i++) {
			String model = "Model" + i;
			build.buildAuto(FILENAME[i]);
			build.printAuto(model);

			for (int j = 0; j < OPSET_SIZE; j++) {
				String opset = "OptionSet" + j;
				String option = "Option" + (int) (Math.random() * 5);
				build.chooseOption(model, opset, option);
			}

			build.printAllChoices(model);
			System.out.println(build.getTotalPrice(model));
		}
	}
}
