package client;

/**
 * Interface for client connection management
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface SocketClientInterface {
	public void openConnection();

	public void handleSession(String command) throws Exception;

	public void closeSession();
}
