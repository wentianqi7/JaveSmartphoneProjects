package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

/**
 * Interface implemented by both BuildCarModelOptions and BuildAuto
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface AutoServer {
	public Automobile createAutoFromProp(Properties props);

	public void addClientModelToMap(Object reqObj);

	public ArrayList<String> provideModels();

	public Serializable serialChosenModel(String model);

	public void printAllAutos();
}
