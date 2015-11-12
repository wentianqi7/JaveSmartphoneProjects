package exception;

import model.Automobile;

/**
 * helper class to fix duplicated opset name
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class FixDupOpset {
	public void fix(Automobile auto, String opsetName, String[] opts,
			String message) {
		// rename the duplicant
		String newName = String.format("%s-Rename", opsetName);
		while (auto.findOpsetByName(newName) != null) {
			newName = String.format("%s-Rename", opsetName);
		}
		// create a new optionset
		try {
			System.out.println(message);
			auto.setOpset(newName, opts);
		} catch (AutoException e) {
			// pass to FixSimple if cannot handle
			FixSimple simple = new FixSimple();
			simple.fix(message);
		}
	}
}
