package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Driver class used for testing
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Server extends Thread implements SocketServerInterface,
		SocketServerConstants {
	private String strHost;
	private int serverPort;
	private ServerSocket serverSock;
	private BuildCarModelOptions build;

	public Server(String strHost, int serverPort) {
		this.strHost = strHost;
		this.serverPort = serverPort;
		build = new BuildCarModelOptions();
	}

	public static void main(String[] args) {
		String strLocalHost = "";
		try {
			strLocalHost = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.err.println("Unable to find local host");
		}
		Server server = new Server(strLocalHost, PORT);
		for (String filename : MODEL_PROPS) {
			server.build.loadAllModels(MODEL_PROPS);
		}
		server.start();
	}

	@Override
	public void openConnection() {
		try {
			serverSock = new ServerSocket(serverPort);
		} catch (IOException e) {
			System.err.printf("%s Could not listen on port: %d\n", strHost, serverPort);
			System.exit(1);
		}
	}

	@Override
	public void handleSession() {
		if (serverSock == null) {
			return;
		}
		try {
			Socket clientSock = serverSock.accept();
			System.out.printf("Accept Connection from client: %s\n", clientSock
					.getPort());
			RequestHandler handler = new RequestHandler(clientSock, build);
			System.out.println("Ready to handle");
			handler.start();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	@Override
	public void run() {
		openConnection();
		System.out.printf("Server %s start listening on %d\n", strHost, serverPort);
		while (true) {
			handleSession();
		}
	}
}
