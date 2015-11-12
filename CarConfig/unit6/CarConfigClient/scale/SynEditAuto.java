package scale;

/**
 * synchronized method to update/delete option/optionset
 * 
 * @author Tianqi Wen (tianqiw)
 */
public interface SynEditAuto {
	public void synUpdateOptionSetName(String model, String opsetName,
			String newName);

	public void synUpdateOptionPrice(String model, String opsetName,
			String optionName, float newPrice);

	public void synDeleteOptionSet(String model, String opsetName);

	public void synDeleteOption(String model, String opsetName,
			String optionName);

	// non-synchronized methods for testing only
	public void nonSynUpdateOptionPrice(String model, String opsetName,
			String optionName, float newPrice);

	public void nonSynDeleteOption(String model, String opsetName,
			String optionName);
}
