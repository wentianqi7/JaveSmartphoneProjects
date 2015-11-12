package exception;

import model.Automobile;

/**
 * fix missing option exception by adding default options
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class FixMissingOption {
	private static final String DEF_OPT = "Default Option:0";

	public void fix(Automobile auto, int extraSize, String opsetName,
			String message) {
		// create default options and add to optionset
		String[] defOpts = new String[extraSize];
		for (int i = 0; i < defOpts.length; i++) {
			defOpts[i] = DEF_OPT;
		}
		try {
			System.out.println(message);
			auto.setOpset(opsetName, defOpts);
		} catch (AutoException e) {
			// pass to FixSimple if cannot handle
			FixSimple simple = new FixSimple();
			simple.fix(message);
		}
	}
}
