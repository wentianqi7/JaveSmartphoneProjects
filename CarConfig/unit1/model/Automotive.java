package model;

import java.io.Serializable;

/**
 * Automotive class represents a car model
 * @author Tianqi Wen (tianqiw)
 */
public class Automotive implements Serializable {
	private static final long serialVersionUID = 5628317585597511884L;
	private String name;
	private float basePrice;
	private OptionSet[] opsets;
	
	public Automotive() {
		name = null;
		basePrice = 0f;
		opsets = null;
	}
	
	public Automotive(String name, float basePrice, int size) {
		this.name = name;
		this.basePrice = basePrice;
		opsets = new OptionSet[size];
		for (int i = 0; i < size; i++) {
			opsets[i] = new OptionSet();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return opsets[index];
	}

	public void setOpsetByIndex(int index, String name, int size, String[] opts) {
		OptionSet temp = new OptionSet(name, size);
		temp.setOptions(opts);
		opsets[index] = temp;
	}
	
	public void setOpsets(int size, String[] name, int[] opSize, String[][] opsets) {
		for (int i = 0; i < size; i++) {
			setOpsetByIndex(i, name[i], opSize[i], opsets[i]);
		}
	}
	
	public OptionSet findOpsetByName(String name) {
		int index = findOpsetIndexByName(name);
		if (index == -1) {
			return null;
		} else {
			return opsets[index];
		}
	}
	
	public int findOpsetIndexByName(String name) {
		for (int i = 0; i < opsets.length; i++) {
			if (opsets[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean deleteOpset(String name) {
		int toDel = findOpsetIndexByName(name);
		if (toDel == -1) {
			return false;
		} else {
			OptionSet[] temp = new OptionSet[opsets.length - 1];
			System.arraycopy(opsets, 0, temp, 0, toDel);
			System.arraycopy(opsets, toDel + 1, temp, toDel, temp.length - toDel);
			opsets = temp;
			return true;
		}
	}
	
	public boolean updateOptionSet(String name, String[] opts) {
		try {
			int toUpdate = findOpsetIndexByName(name);
			opsets[toUpdate].setOptions(opts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ").append(name).append("\n");
		sb.append("base price: ").append(basePrice).append("\n");
		for (int i = 0; i < opsets.length; i++) {
			sb.append("OptionSet ").append(i).append(" :\n").append(opsets[i].print());
		}
		return sb.toString();
	}
}
