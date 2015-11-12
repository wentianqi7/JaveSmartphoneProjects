package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;
import servlets.AutoServlet;
import util.Constants;

/**
 * Driver class for client
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Client implements AutoServlet, SocketClientConstants, Constants {
	private int serverPort;
	private String serverIp;
	private Socket sock;
	ObjectInputStream reader;
	ObjectOutputStream writer;
	CarModelOptionsIO carIO;
	ArrayList<String> modelList;

	public Client() {
		this.serverPort = SERVER_PORT;
		this.serverIp = SERVER_IP;
		modelList = new ArrayList<String>();
	}

	@Override
	public void openConnection() {
		try {
			sock = new Socket(serverIp, serverPort);
			writer = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}

	@Override
	public ArrayList<String> showModelList() throws Exception {
		sendRequest(SHOW_MODEL, FileType.MESSAGE);
		Object response = readRespondObject();
		if (response instanceof ArrayList<?>) {
			System.out.println("Available Models on Server:");
			for (Object model : (ArrayList<?>) response) {
				if (model instanceof String) {
					modelList.add((String) model);
				} else {
					throw new ClassNotFoundException();
				}
			}
		} else if (response instanceof String) {
			// failed
			return null;
		}
		return modelList;
	}

	@Override
	public void getSelectedModel(String target) throws Exception {
		sendRequest(target, FileType.MESSAGE);
		Object response = readRespondObject();
		if (response instanceof Serializable) {
			carIO.buildAutoFromSerial((Serializable) response);
		} else {
			System.err.println("Cannot load serilized file");
		}
	}

	@Override
	public ArrayList<String> getOpsetNames() {
		System.out.println(carIO);
		return carIO.getOpsetNames();
	}

	@Override
	public ArrayList<String> getOptionNames(String opsetName) {
		return carIO.getOptionNames(opsetName);
	}

	@Override
	public void closeSession() {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		if (sock != null) {
			try {
				sock.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public int getOptionPrice(String opsetName, String optionName) {
		return carIO.getOptionPrice(opsetName, optionName);
	}

	@Override
	public int getBasePrice() {
		return carIO.getBasePrice();
	}
	
	@Override
	public String getMake() {
		return carIO.getMake();
	}

	public void sendRequest(String target, FileType ft) throws IOException {
		if (carIO == null) {
			System.out.println("init cario");
			carIO = new CarModelOptionsIO(writer);
		}

		switch (ft) {
		case PROPERTIES:
			Properties toSend = carIO.loadProps(target);
			carIO.sendProps(toSend);
			break;
		case TXT:

			break;
		case MESSAGE:
			carIO.sendMessage(target);
			break;
		default:
			System.err.println("Invalid file type!!!");
			throw new IOException();
		}
	}

	public Object readRespondObject() throws ClassNotFoundException,
			IOException {
		if (sock == null) {
			System.err.println("Socket is null");
			return null;
		}
		if (reader == null) {
			reader = new ObjectInputStream(sock.getInputStream());
		}
		Object obj = reader.readObject();

		return obj;
	}
}
