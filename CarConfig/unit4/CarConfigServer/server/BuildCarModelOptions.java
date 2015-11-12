package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

/**
 * Accept properties object from client socket over an ObjectStream and create
 * an Automobile
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class BuildCarModelOptions implements AutoServer {

	private BuildAuto build;

	public BuildCarModelOptions() {
		build = new BuildAuto();
	}

	public Automobile createAutoFromProp(Properties props) {
		return build.createAutoFromProp(props);
	}

	public void addClientModelToMap(Object reqObj) {
		build.addClientModelToMap(reqObj);
	}

	public ArrayList<String> provideModels() {
		return build.provideModels();
	}

	public Serializable serialChosenModel(String model) {
		return build.serialChosenModel(model);
	}

	public void printAllAutos() {
		build.printAllAutos();
	}
}
