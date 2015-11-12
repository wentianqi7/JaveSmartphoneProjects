package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import model.Automobile;
import model.OptionSet;
import util.Util;

/**
 * IO of car model
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class CarModelOptionsIO {
	private Automobile tempAuto;
	private ObjectOutputStream writer;

	public CarModelOptionsIO(ObjectOutputStream out) {
		tempAuto = null;
		writer = out;
	}

	public Properties loadProps(String filename) {
		Util util = new Util();
		Properties props = util.load(filename);
		return props;
	}

	public void sendProps(Properties props) throws IOException {
		if (props == null) {
			System.out.println("Properties is null");
			return;
		}
		if (writer == null) {
			System.out.println("No ObjectOutputStream provided yet");
			return;
		}
		writer.writeObject(props);
		System.out.println("Send Properties Object to server");
	}

	public void sendMessage(String message) throws IOException {
		if (message == null) {
			System.out.println("Message is null");
			return;
		}
		if (writer == null) {
			System.out.println("No ObjectOutputStream provided yet");
			return;
		}
		writer.writeObject(message);
		System.out.println("Send Message to server");
	}

	public void buildAutoFromSerial(Serializable serilz)
			throws ClassNotFoundException {
		if (serilz instanceof Automobile) {
			tempAuto = (Automobile) serilz;
		} else {
			throw new ClassNotFoundException();
		}
	}

	/**
	 * Read and print user choice for the temporariy model received from server
	 * 
	 * @throws NullPointerException
	 */
	public void printClientChoice() throws NullPointerException {
		if (tempAuto == null) {
			return;
		}
		System.out.println(tempAuto.toString());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String buffer = null;

		ArrayList<String> opsetNames = tempAuto.getOptionSetNames();
		for (String opsetName : opsetNames) {
			// read choice from user
			ArrayList<String> optionNames = tempAuto.getOptionNames(opsetName);
			StringBuilder sb = new StringBuilder();
			for (String optionName : optionNames) {
				sb.append(optionName).append("\n");
			}
			System.out.println(sb.toString());
			System.out.printf("Please choose one option for %s\n", opsetName);
			try {
				buffer = br.readLine();
			} catch (IOException e) {
				System.out.println("Failed to read choice");
			}
			tempAuto.setOptionChoice(opsetName, buffer);
		}
		StringBuilder sb = new StringBuilder();
		HashMap<String, String> choices = tempAuto.getAllChoice();
		for (String opsetName : choices.keySet()) {
			sb.append(opsetName).append(": ").append(choices.get(opsetName))
					.append("\n");
		}
		System.out.println(sb.toString());
		tempAuto = null;
	}

	public ArrayList<String> getOpsetNames() {
		return tempAuto.getOptionSetNames();
	}

	public ArrayList<String> getOptionNames(String opsetName) {
		return tempAuto.getOptionNames(opsetName);
	}
	
	public int getOptionPrice(String opsetName, String optionName) {
		return tempAuto.getOptionPrice(opsetName, optionName);
	}
	
	public int getBasePrice() {
		return (int) tempAuto.getBasePrice();
	}
	
	public String getMake() {
		return tempAuto.getMake();
	}
}
