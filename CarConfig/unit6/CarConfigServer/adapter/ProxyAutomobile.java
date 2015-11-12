package adapter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import model.Automobile;
import exception.AutoException;
import scale.EditCmd.Command;
import scale.EditOptions;
import server.BuildCarModelOptions;
import util.Util;

/**
 * proxy class that implements all the methods declared in interface
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public abstract class ProxyAutomobile {
	private static AutoCollection autos = new AutoCollection();
	private static DatabaseAdapter dbAdp;

	public void buildAuto(String filename, String fileType) {
		Util util = new Util();

		Automobile auto = new Automobile();
		switch (fileType) {
		case ".txt":
			auto = util.buildAutoObject(filename + fileType, auto);
			break;
		case ".props":
			auto = util.loadAutoFromProp(filename + fileType, auto);
		}

		autos.addAuto(auto);
		dbAdp.dbAddAuto(auto);
	}

	public void printAuto(String model) {
		Automobile auto = autos.getAuto(model);
		if (auto != null) {
			System.out.println(auto.toString());
		} else {
			System.out.println(String.format("Model Not Found: %s", model));
		}
	}

	public void printAllAutos() {
		System.out.println(autos.toString());
	}

	public void initDB(String createFilename, String dropFilename) {
		dbAdp = new DatabaseAdapter();
		dbAdp.dbDrop(dropFilename);
		dbAdp.dbCreate(createFilename);
	}

	public void showModelDB(String model) {
		try {
			dbAdp.dbShowAutoInfo(model);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void addClientModelToMap(Object reqObj) {
		BuildCarModelOptions builder = new BuildCarModelOptions();
		if (reqObj instanceof Properties) {
			Automobile auto = builder.createAutoFromProp((Properties) reqObj);
			autos.addAuto(auto);
		} else {
			System.out.println("ERROR: Invalid Client Request Object Type "
					+ reqObj.getClass());
		}
	}

	public void loadAllModels(String[] filename) {
		Util util = new Util();
		for (String fn : filename) {
			autos.addAuto(util.loadAutoFromProp(fn, new Automobile()));
		}
	}

	public ArrayList<String> provideModels() {
		return autos.getAllModels();
	}

	public Serializable serialChosenModel(String model) {
		return autos.getAuto(model);
	}

	public Automobile createAutoFromProp(Properties props) {
		Util util = new Util();
		Automobile auto = util.createAutoFromProp(props, new Automobile());
		return auto;
	}

	public void updateOptionSetName(String model, String opsetName,
			String newName) {
		Automobile auto = autos.getAuto(model);
		if (auto != null) {
			auto.updateOptionSetName(opsetName, newName);
			dbAdp.dbUpdateAutoOpset(model, opsetName, newName);
		}
	}

	public void updateOptionPrice(String model, String opsetName,
			String optionName, float newPrice) {
		Automobile auto = autos.getAuto(model);
		if (auto != null) {
			auto.updateOptionPrice(opsetName, optionName, newPrice);
			dbAdp.dbUpdateAutoPrice(model, opsetName, optionName, newPrice);
		}
	}

	public void fix(AutoException ae, String model) {
		ae.fix(autos.getAuto(model));
	}

	public void chooseOption(String model, String opsetName, String optName) {
		autos.getAuto(model).setOptionChoice(opsetName, optName);
	}

	public void printAllChoices(String model) {
		StringBuilder sb = new StringBuilder();
		HashMap<String, String> choices = autos.getAuto(model).getAllChoice();
		for (String opsetName : choices.keySet()) {
			sb.append(opsetName).append(": ").append(choices.get(opsetName))
					.append("\n");
		}
		System.out.println(sb.toString());
	}

	public void printChoice(String model, String opsetName) {
		System.out.println(autos.getAuto(model).getOptionSetChoice(opsetName));
	}

	public int getTotalPrice(String model) {
		return autos.getAuto(model).getTotalPrice();
	}

	public int getChoicePrice(String model, String opsetName) {
		return autos.getAuto(model).getOptionSetChoicePrice(opsetName);
	}

	public AutoCollection getAutos() {
		return autos;
	}

	public void deleteAuto(String model) {
		autos.deleteAuto(model);
		dbAdp.dbDeleteAuto(model);
	}

	public void synUpdateOptionSetName(String model, String opsetName,
			String newName) {
		EditOptions edit = new EditOptions(autos);
		edit.addCmd(Command.synUpdateOptionSetName, model, opsetName, newName);
		edit.start();
	}

	public void synUpdateOptionPrice(String model, String opsetName,
			String optionName, float newPrice) {
		EditOptions edit = new EditOptions(autos);
		edit.addCmd(Command.synUpdateOptionPrice, model, opsetName, optionName,
				newPrice);
		edit.start();
	}

	public void synDeleteOptionSet(String model, String opsetName) {
		EditOptions edit = new EditOptions(autos);
		edit.addCmd(Command.synDeleteOptionSet, model, opsetName);
		edit.start();
	}

	public void synDeleteOption(String model, String opsetName,
			String optionName) {
		EditOptions edit = new EditOptions(autos);
		edit.addCmd(Command.synDeleteOption, model, opsetName, optionName);
		edit.start();
	}

	public void nonSynUpdateOptionPrice(String model, String opsetName,
			String optionName, float newPrice) {
		EditOptions edit = new EditOptions(autos);
		edit.addCmd(Command.nonSynUpdateOptionPrice, model, opsetName,
				optionName, newPrice);
		edit.start();
	}

	public void nonSynDeleteOption(String model, String opsetName,
			String optionName) {
		EditOptions edit = new EditOptions(autos);
		edit.addCmd(Command.nonSynDeleteOption, model, opsetName, optionName);
		edit.start();
	}
}
