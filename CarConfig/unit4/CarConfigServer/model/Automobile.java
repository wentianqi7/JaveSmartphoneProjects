package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.OptionSet.Option;
import exception.AutoException;

/**
 * Automotive class represents a car model
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Automobile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String make = null;
	private String model = null;
	private float basePrice = 0f;
	private ArrayList<OptionSet> opsets = new ArrayList<OptionSet>();
	private ArrayList<Option> choice = new ArrayList<Option>();

	public Automobile() {

	}

	public Automobile(String make, String model, float basePrice) {
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
	}

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

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @param index
	 * @return OptionSet with index
	 */
	public OptionSet getOpsetByIndex(int index) {
		return opsets.get(index);
	}

	public int getOptionPrice(String opsetName, String optionName) {
		return (int) findOpsetByName(opsetName).findOptionByName(optionName)
				.getPrice();
	}

	public void setOpset(String name, String[] opts) throws AutoException {
		int toAdd = findOpsetIndexByName(name);
		if (toAdd == -1) {
			OptionSet temp = new OptionSet(name);
			temp.setOptions(opts);
			opsets.add(temp);
		} else {
			opsets.get(toAdd).setOptions(opts);
		}

	}

	public void setOpsets(int size, String[] name, String[][] opsets)
			throws AutoException {
		for (int i = 0; i < size; i++) {
			setOpset(name[i], opsets[i]);
		}
	}

	public OptionSet findOpsetByName(String name) {
		int index = findOpsetIndexByName(name);
		if (index == -1) {
			return null;
		} else {
			return opsets.get(index);
		}
	}

	public int findOpsetIndexByName(String name) {
		for (int i = 0; i < opsets.size(); i++) {
			if (opsets.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public boolean deleteOptionSet(String name) {
		int toDel = findOpsetIndexByName(name);
		try {
			System.out.println("delete: " + opsets.get(toDel).getName());
			opsets.remove(toDel);
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The optionset " + name + " does not exists.");
			return false;
		}
	}

	public boolean deleteOption(String opsetName, String optionName) {
		try {
			this.findOpsetByName(opsetName).deleteOption(optionName);
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * update optionset with new name
	 * 
	 * @param optionSetName
	 * @param newName
	 * @return if successful
	 */
	public boolean updateOptionSetName(String optionSetName, String newName) {
		try {
			findOpsetByName(optionSetName).setName(newName);
			return true;
		} catch (NullPointerException e) {
			System.out.println("OptionSet " + optionSetName + " does not exist.");
			return false;
		}
	}

	/**
	 * update option with new price
	 * 
	 * @param optionSetName
	 * @param optionName
	 * @param newPrice
	 * @return if successful
	 */
	public boolean updateOptionPrice(String optionSetName, String optionName,
			float newPrice) {
		try {
			findOpsetByName(optionSetName).updateOptionPrice(optionName, newPrice);
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public HashMap<String, String> getAllChoice() {
		HashMap<String, String> choices = new HashMap<String, String>();
		for (OptionSet opset : opsets) {
			choices.put(opset.getName(), opset.getOptionChoice().getName());
		}
		return choices;
	}

	public String getOptionSetChoice(String opsetName) {
		try {
			Option opt = this.findOpsetByName(opsetName).getOptionChoice();
			return opt.getName();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public int getTotalPrice() {
		int total = (int) basePrice;
		for (OptionSet opset : opsets) {
			try {
				total += opset.getOptionChoice().getPrice();
			} catch (NullPointerException e) {
				total += 0;
			}
		}
		return total;
	}

	public int getOptionSetChoicePrice(String opsetName) {
		Option opt = this.findOpsetByName(opsetName).getOptionChoice();
		return (int) opt.getPrice();
	}

	public void setOptionChoice(String opsetName, String optionName) {
		this.findOpsetByName(opsetName).setOptionChoice(optionName);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("make: ").append(make).append("\nmodel: ").append(model)
				.append("\n");
		sb.append("base price: ").append(basePrice).append("\n");
		for (int i = 0; i < opsets.size(); i++) {
			sb.append("OptionSet ").append(i).append(" :\n")
					.append(opsets.get(i).print());
		}
		return sb.toString();
	}
}
