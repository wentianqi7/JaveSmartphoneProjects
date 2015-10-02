package scale;

import java.util.ArrayList;
import scale.EditCmd.Command;

/**
 * Clarifications: I put all the synchronized blocks in EditOptions since we
 * should synchronize methods in EditOption class to isolate multithreading and
 * synchronization
 * 
 * An edit thread to update/delete option/optionset
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class EditOptions extends Thread {
	private static final String ARG_ERROR = "Arguments do not match! Expected: %d, Received: %d";
	private AutoCollection autos;
	private ArrayList<EditCmd> cmds = new ArrayList<EditCmd>();

	public EditOptions(AutoCollection autos) {
		this.autos = autos;
	}

	public void addCmd(Command name, Object... inputs) {
		cmds.add(new EditCmd(name, inputs));
	}

	public void synUpdateOptionSetName(String model, String opsetName,
			String newName) {
		synchronized (autos) {
			autos.getAuto(model).updateOptionSetName(opsetName, newName);
		}
	}

	public void synUpdateOptionPrice(String model, String opsetName,
			String optionName, float newPrice) {
		synchronized (autos) {
			autos.getAuto(model).updateOptionPrice(opsetName, optionName,
					newPrice);
		}
	}

	public void synDeleteOptionSet(String model, String opsetName) {
		synchronized (autos) {
			autos.getAuto(model).deleteOptionSet(opsetName);
		}
	}

	public void synDeleteOption(String model, String opsetName,
			String optionName) {
		synchronized (autos) {
			autos.getAuto(model).deleteOption(opsetName, optionName);
		}
	}

	public void nonSynUpdateOptionPrice(String model, String opsetName,
			String optionName, float newPrice) {
		autos.getAuto(model).updateOptionPrice(opsetName, optionName, newPrice);
	}

	public void nonSynDeleteOption(String model, String opsetName,
			String optionName) {
		autos.getAuto(model).deleteOption(opsetName, optionName);
	}

	@Override
	public void run() {
		for (EditCmd cmd : cmds) {
			ArrayList<Object> inputs = cmd.getInputs();
			switch (cmd.getCmd()) {
			// determine which command to execute
			case synUpdateOptionSetName:
				if (inputs.size() != 3) {
					System.out.println(String.format(ARG_ERROR, 3,
							inputs.size()));
					continue;
				}
				this.synUpdateOptionSetName((String) inputs.get(0),
						(String) inputs.get(1), (String) inputs.get(2));
				break;
			case synUpdateOptionPrice:
				if (inputs.size() != 4) {
					System.out.println(String.format(ARG_ERROR, 4,
							inputs.size()));
					continue;
				}
				this.synUpdateOptionPrice((String) inputs.get(0),
						(String) inputs.get(1), (String) inputs.get(2),
						(float) inputs.get(3));
				break;
			case synDeleteOptionSet:
				if (inputs.size() != 2) {
					System.out.println(String.format(ARG_ERROR, 2,
							inputs.size()));
					continue;
				}
				this.synDeleteOptionSet((String) inputs.get(0),
						(String) inputs.get(1));
				System.out.println("OptionSet is deleted: "
						+ (String) inputs.get(1));
				break;
			case synDeleteOption:
				if (inputs.size() != 3) {
					System.out.println(String.format(ARG_ERROR, 3,
							inputs.size()));
					continue;
				}
				this.synDeleteOption((String) inputs.get(0),
						(String) inputs.get(1), (String) inputs.get(2));
				break;
			case nonSynUpdateOptionPrice:
				if (inputs.size() != 4) {
					System.out.println(String.format(ARG_ERROR, 4,
							inputs.size()));
					continue;
				}
				this.nonSynUpdateOptionPrice((String) inputs.get(0),
						(String) inputs.get(1), (String) inputs.get(2),
						(float) inputs.get(3));
				break;
			case nonSynDeleteOption:
				if (inputs.size() != 3) {
					System.out.println(String.format(ARG_ERROR, 3,
							inputs.size()));
					continue;
				}
				this.nonSynDeleteOption((String) inputs.get(0),
						(String) inputs.get(1), (String) inputs.get(2));
			}
		}
	}
}
