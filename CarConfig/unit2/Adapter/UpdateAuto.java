package Adapter;

/**
 * interface for update operations
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname,
			String newName);

	public void updateOptionPrice(String Modelname, String Optionname,
			String Option, float newprice);
}
