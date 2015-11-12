package scale;

import java.util.ArrayList;

/**
 * class for a command to be executed in EditOptions
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class EditCmd {
	// command type
	public static enum Command {
		// synchronized methods
		synUpdateOptionSetName, synUpdateOptionPrice, synDeleteOptionSet, synDeleteOption,
		// for testing only
		nonSynUpdateOptionPrice, nonSynDeleteOption
	}

	private Command cmd;
	private ArrayList<Object> inputs = new ArrayList<Object>();

	public EditCmd(Command cmd, Object[] input) {
		this.cmd = cmd;
		for (int i = 0; i < input.length; i++) {
			inputs.add(input[i]);
		}
	}

	public Command getCmd() {
		return cmd;
	}

	public ArrayList<Object> getInputs() {
		return inputs;
	}
}
