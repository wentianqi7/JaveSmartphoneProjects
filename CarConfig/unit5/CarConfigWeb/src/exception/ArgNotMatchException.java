package exception;

/**
 * throw when user input argument length is not correct
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class ArgNotMatchException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message = "";

	public ArgNotMatchException() {
		super();
	}

	public ArgNotMatchException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}
}
