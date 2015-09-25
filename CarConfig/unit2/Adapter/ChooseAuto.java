package Adapter;

/**
 * make choice and print the options
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface ChooseAuto {
	public void chooseOption(String modelName, String opsetName, String optName);

	public void printAllChoices(String modelName);

	public void printChoice(String modelName, String opsetName);
	
	public int getTotalPrice(String modelName);
	
	public int getChoicePrice(String modelName, String opsetName);
}
