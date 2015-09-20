package model;

import java.io.Serializable;

/**
 * represents a optionset of a automotive. contains all option values of the
 * optionset
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class OptionSet implements Serializable {

	/**
	 * represents a single option with name and price
	 */
	protected class Option implements Serializable {
		private static final long serialVersionUID = -750978252384574185L;
		protected String name;
		protected float price;

		protected Option() {
			name = null;
			price = 0f;
		}

		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected float getPrice() {
			return price;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		/**
		 * print option properties
		 * 
		 * @return option properties in string format
		 */
		protected String print() {
			StringBuilder sb = new StringBuilder();
			sb.append("\t\tname: ").append(name).append("\n");
			sb.append("\t\tprice: ").append(price).append("\n");
			return sb.toString();
		}
	}

	private static final long serialVersionUID = -6854983571473480445L;
	private Option[] opts;
	private String name;

	protected OptionSet() {
		name = null;
		opts = null;
	}

	protected OptionSet(String name, int size) {
		opts = new Option[size];
		this.name = name;

		for (int i = 0; i < size; i++) {
			opts[i] = new Option();
		}
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected Option getOptionByIndex(int index) {
		return opts[index];
	}

	/**
	 * set value of a single option with given index
	 * 
	 * @param index
	 * @param name
	 * @param price
	 */
	protected void setOptionByIndex(int index, String name, float price) {
		opts[index].setName(name);
		opts[index].setPrice(price);
	}

	/**
	 * @param opts
	 *            String array of option values
	 */
	protected void setOptions(String[] opts) {
		for (int i = 0; i < opts.length; i++) {
			String[] segs = opts[i].trim().split(":");
			String optName = segs[0];
			float price = Float.parseFloat(segs[1]);
			setOptionByIndex(i, optName, price);
		}
	}

	/**
	 * @param name
	 * @return option object by given name
	 */
	protected Option findOptionByName(String name) {
		int index = findOptionIndexByName(name);
		if (index == -1) {
			return null;
		} else {
			return opts[index];
		}
	}

	/**
	 * find the index of option in opts array by name
	 * 
	 * @param name
	 * @return the index of option, return -1 when not found
	 */
	protected int findOptionIndexByName(String name) {
		for (int i = 0; i < opts.length; i++) {
			if (opts[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * delete option by name
	 * 
	 * @param name
	 * @return whether the deletion is successful
	 */
	protected boolean deleteOption(String name) {
		int toDel = findOptionIndexByName(name);
		if (toDel == -1) {
			return false;
		} else {
			Option[] temp = new Option[opts.length - 1];
			System.arraycopy(opts, 0, temp, 0, toDel);
			System.arraycopy(opts, toDel + 1, temp, toDel, temp.length - toDel);
			opts = temp;
			return true;
		}
	}

	/**
	 * find option and set value to it
	 * 
	 * @param name
	 * @param price
	 * @return whether the update is successful or not
	 */
	protected boolean updateOption(String name, float price) {
		try {
			int toUpdate = findOptionIndexByName(name);
			opts[toUpdate].setPrice(price);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * print optionSet properties
	 * 
	 * @return optionSet properties in string format
	 */
	protected String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("\tname: ").append(name).append("\n");
		for (int i = 0; i < opts.length; i++) {
			sb.append("\tOption ").append(i).append(":\n")
					.append(opts[i].print());
		}
		return sb.toString();
	}
}
