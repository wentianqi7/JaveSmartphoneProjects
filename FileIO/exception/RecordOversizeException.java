package exception;

/**
 * @author Tianqi Wen (tianqiw)
 * Throwed when the record size if greater than limitation
 */
public class RecordOversizeException extends Exception {

	private String message = null;

	public RecordOversizeException() {
		super();
	}

	public RecordOversizeException(String message) {
		super(message);
		this.message = message;
	}

	public RecordOversizeException(Throwable cause) {
		super(cause);
	}

	public RecordOversizeException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}
}
