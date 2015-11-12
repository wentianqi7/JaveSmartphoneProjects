package servlets;

import java.util.ArrayList;

import client.Client;

/**
 * declared methods used by servlets to interact with client
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class BuildClientCommand implements AutoServlet {
	private Client client;

	public BuildClientCommand() {
		client = new Client();
	}

	@Override
	public void openConnection() {
		client.openConnection();
	}

	@Override
	public void closeSession() {
		client.closeSession();
	}

	@Override
	public ArrayList<String> showModelList() throws Exception {
		return client.showModelList();
	}

	@Override
	public void getSelectedModel(String target) throws Exception {
		client.getSelectedModel(target);
	}

	@Override
	public ArrayList<String> getOpsetNames() {
		return client.getOpsetNames();
	}

	@Override
	public ArrayList<String> getOptionNames(String opsetName) {
		return client.getOptionNames(opsetName);
	}

	@Override
	public int getOptionPrice(String opsetName, String optionName) {
		return client.getOptionPrice(opsetName, optionName);
	}

	@Override
	public int getBasePrice() {
		return client.getBasePrice();
	}

	@Override
	public String getMake() {
		return client.getMake();
	}
}
