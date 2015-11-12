package servlets;

import java.util.ArrayList;

/**
 * Interface for between client and servlets
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface AutoServlet {
	public void openConnection();

	public void closeSession();

	public ArrayList<String> showModelList() throws Exception;

	public void getSelectedModel(String target) throws Exception;
	
	public ArrayList<String> getOpsetNames();
	
	public ArrayList<String> getOptionNames(String opsetName);
	
	public int getOptionPrice(String opsetName, String optionName);
	
	public int getBasePrice();
	
	public String getMake();
}
