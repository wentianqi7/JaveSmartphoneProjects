package Adapter;

import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Automobile;
import exception.AutoException;
import util.Util;

/**
 * proxy class that implements all the methods declared in interface
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public abstract class ProxyAutomobile {
	private LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();

	public void buildAuto(String filename) {
		Util util = new Util();
		Automobile auto = util.buildAutoObject(filename, new Automobile());
		autos.put(auto.getModel(), auto);
	}

	public void printAuto(String modelname) {
		if (autos.containsKey(modelname)) {
			System.out.println(autos.get(modelname).toString());
		} else {
			System.out.println(String.format("Model Not Found: %s", modelname));
		}
	}

	public void updateOptionSetName(String modelname, String optionSetName,
			String newName) {
		if (autos.containsKey(modelname)) {
			autos.get(modelname).updateOptionSetName(optionSetName, newName);
		}
	}

	public void updateOptionPrice(String modelname, String optionSetName,
			String optionName, float newPrice) {
		if (autos.containsKey(modelname)) {
			autos.get(modelname).updateOptionPrice(optionSetName, optionName,
					newPrice);
		}
	}

	public void fix(AutoException ae, String modelName) {
		ae.fix(autos.get(modelName));
	}

	public void chooseOption(String modelName, String opsetName, String optName) {
		autos.get(modelName).setOptionChoice(opsetName, optName);
	}

	public void printAllChoices(String modelName) {
		StringBuilder sb = new StringBuilder();
		HashMap<String, String> choices = autos.get(modelName).getAllChoice();
		for (String opsetName : choices.keySet()) {
			sb.append(opsetName).append(": ").append(choices.get(opsetName))
					.append("\n");
		}
		System.out.println(sb.toString());
	}

	public void printChoice(String modelName, String opsetName) {
		System.out.println(autos.get(modelName).getOptionSetChoice(opsetName));
	}
	
	public int getTotalPrice(String modelName) {
		return autos.get(modelName).getTotalPrice();
	}
	
	public int getChoicePrice(String modelName, String opsetName) {
		return autos.get(modelName).getOptionSetChoicePrice(opsetName);
	}
}
