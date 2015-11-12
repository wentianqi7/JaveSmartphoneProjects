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

import util.Constants;

/**
 * Driver class for client
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Client extends Thread implements SocketClientInterface,
		SocketClientConstants, Constants {
	private int serverPort;
	private String serverIp;
	private Socket sock;
	ObjectInputStream reader;
	ObjectOutputStream writer;
	CarModelOptionsIO carIO;
	ArrayList<String> modelList;

	public Client(int serverPort, String serverIp) {
		this.serverPort = serverPort;
		this.serverIp = serverIp;
		modelList = new ArrayList<String>();
	}

	public static void main(String[] args) {
		Client client = new Client(SERVER_PORT, SERVER_IP);
		client.start();
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
	public void handleSession(String input) throws Exception {
		if (input == null) {
			return;
		}

		int index = (input.indexOf(' ') == -1) ? input.length() : input.indexOf(' ');
		String command = input.substring(0, index);

		switch (command) {
		case "send":
			String target = input.substring(input.indexOf(' ') + 1);
			sendRequest(target, FileType.PROPERTIES);
			Object response = readRespondObject();
			if (response instanceof String) {
				System.out.println(response);
			}
			break;
		case "show":
			sendRequest(SHOW_MODEL, FileType.MESSAGE);
			response = readRespondObject();
			if (response instanceof ArrayList<?>) {
				System.out.println("Available Models on Server:");
				for (Object model : (ArrayList<?>) response) {
					if (model instanceof String) {
						modelList.add((String) model);
						System.out.println(model);
					} else {
						throw new ClassNotFoundException();
					}
				}
			} else if (response instanceof String) {
				// failed
				System.out.println(response);
			}
			break;
		case "select":
			target = input.substring(input.indexOf(' ') + 1);
			if (modelList.contains(target)) {
				sendRequest(target, FileType.MESSAGE);
				response = readRespondObject();
				if (response instanceof Serializable) {
					carIO.buildAutoFromSerial((Serializable) response);
					carIO.printClientChoice();
				} else {
					System.err.println("Cannot load serilized file");
				}
			} else {
				System.err.println("Model not available");
			}
			break;
		case "quit":
			sendRequest(CLOSE, FileType.MESSAGE);
			response = readRespondObject();
			if (response instanceof String && ((String) response).equals(CLOSE)) {
				closeSession();
				System.out.println("Bye.");
				System.exit(0);
			} else {
				System.err.println("Failed to close session");
				System.exit(1);
			}
		default:
			System.err.println("Undefined Command!!!");
		}
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

	public void sendRequest(String target, FileType ft) throws IOException {
		if (carIO == null) {
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

	@Override
	public void run() {
		openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String buffer = null;
		while (true) {
			// read command from user
			try {
				buffer = br.readLine();
			} catch (IOException e) {
				System.out.println("Failed to read command");
			}

			// handle command
			try {
				handleSession(buffer);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
