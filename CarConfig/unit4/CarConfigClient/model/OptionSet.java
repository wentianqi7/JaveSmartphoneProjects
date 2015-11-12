package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import exception.CustomException;

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
		private static final long serialVersionUID = 1L;
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

	private static final long serialVersionUID = 1L;
	private ArrayList<Option> opts = new ArrayList<Option>();
	private Option choice = null;
	private String name = null;

	protected OptionSet() {
 
	}

	protected OptionSet(String name) {
		this.name = name;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	protected Option getOptionByIndex(int index) {
		return opts.get(index);
	}

	/**
	 * set value of a single option with given index
	 * 
	 * @param index
	 * @param name
	 * @param price
	 */
	protected void setOption(String name, float price) {
		opts.add(new Option(name, price));
	}

	protected ArrayList<Option> getOptions() {
		return opts;
	}
	
	/**
	 * @param opts
	 *            String array of option values
	 * @throws AutoException
	 */
	protected void setOptions(String[] opts) throws AutoException {
		for (String opt : opts) {
			String[] segs = opt.trim().split(":");
			String optName = segs[0];
			float price = 0;
			try {
				price = Float.parseFloat(segs[1]);
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				// If price cannot be properly set
				// throw MISSING_PRICE
				throw new AutoException(CustomException.MISSING_PRICE, this.name, optName);
			}
			setOption(optName, price);
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
			return opts.get(index);
		}
	}

	/**
	 * find the index of option in opts array by name
	 * 
	 * @param name
	 * @return the index of option, return -1 when not found
	 */
	protected int findOptionIndexByName(String name) {
		for (int i = 0; i < opts.size(); i++) {
			if (opts.get(i).getName().equals(name)) {
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
		try {
			System.out.println("delete: " + opts.get(toDel).getName());
			opts.remove(toDel);
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The option " + name + " does not exists.");
			return false;
		}
	}

	/**
	 * find option and set new price to it
	 * 
	 * @param name
	 * @param price
	 * @return whether the update is successful or not
	 */
	protected boolean updateOptionPrice(String name, float price) {
		try {
			int toUpdate = findOptionIndexByName(name);
			opts.get(toUpdate).setPrice(price);
			System.out.println("update: " + opts.get(toUpdate).getPrice());
			return true;
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			System.out.println("Option name not found.");
			return false;
		}
	}
	
	protected Option getOptionChoice() {
		return choice;
	}
	
	protected void setOptionChoice(String optionName) {
		choice = this.findOptionByName(optionName);
	}

	/**
	 * print optionSet properties
	 * 
	 * @return optionSet properties in string format
	 */
	protected String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("\tname: ").append(name).append("\n");
		for (int i = 0; i < opts.size(); i++) {
			sb.append("\tOption ").append(i).append(":\n")
					.append(opts.get(i).print());
		}
		return sb.toString();
	}
}
