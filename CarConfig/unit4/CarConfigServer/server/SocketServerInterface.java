package server;

/**
 * Interface for server connectin management
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface SocketServerInterface {
	public void openConnection();

	public void handleSession();
}
