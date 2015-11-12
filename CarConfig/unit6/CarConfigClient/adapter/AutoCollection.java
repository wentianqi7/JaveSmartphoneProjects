package adapter;

import java.util.LinkedHashMap;

import model.Automobile;

/**
 * LinkedHashMap structure to store all the models
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class AutoCollection {
	private LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();

	public void addAuto(Automobile auto) {
		if (auto != null) {
			autos.put(auto.getModel(), auto);
		}
	}

	public Automobile getAuto(String model) {
		if (autos.containsKey(model)) {
			return autos.get(model);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (Automobile auto : autos.values()) {
			sb.append("--------- Model ").append(index++)
					.append(" ---------\n");
			sb.append(auto.toString());
			sb.append("\n\n");
		}
		return sb.toString();
	}
}
