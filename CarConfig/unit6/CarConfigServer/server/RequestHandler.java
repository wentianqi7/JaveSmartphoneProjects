package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Properties;

import util.Constants;

/**
 * Handler client request in a separte thread
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class RequestHandler extends Thread implements Constants {
	private Socket clientSock;
	private BuildCarModelOptions build;
	private ObjectInputStream reader;
	private ObjectOutputStream writer;

	public RequestHandler(Socket sock, BuildCarModelOptions build) throws IOException {
		clientSock = sock;
		this.build = build;
		build = new BuildCarModelOptions();
	}

	public Object readRequestObject() {
		if (clientSock == null) {
			System.err.println("Client Socket is null");
			return null;
		}
		Object obj = null;
		try {
			if (reader == null) {
				reader = new ObjectInputStream(clientSock.getInputStream());
			}
			obj = reader.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
		return obj;
	}

	public void sendResponse(Object resp) {
		try {
			if (writer == null) {
				writer = new ObjectOutputStream(clientSock.getOutputStream());
			}
			writer.writeObject(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	}

	@Override
	public void run() {
		while (true) {
			Object clientReq = readRequestObject();

			if (clientReq instanceof Properties) {
				// build Automobile from Properites Object
				build.addClientModelToMap(clientReq);
				build.printAllAutos();
				sendResponse(SUCCESS);
			} else if (clientReq instanceof String) {
				// return a list of available autos
				String reqStr = (String) clientReq;
				switch (reqStr) {
				case SHOW_MODEL:
					sendResponse(build.provideModels());
					break;
				case CLOSE:
					sendResponse(CLOSE);
					closeSession();
					Thread.interrupted();
					break;
				default:
					Serializable serilz = build.serialChosenModel(reqStr);
					if (serilz != null) {
						sendResponse(serilz);
					} else {
						System.err.println("Invalid Request");
						sendResponse(FAILED);
					}
				}
			} else {
				System.out.println("Quit Handler");
				break;
			}
		}
	}
}
