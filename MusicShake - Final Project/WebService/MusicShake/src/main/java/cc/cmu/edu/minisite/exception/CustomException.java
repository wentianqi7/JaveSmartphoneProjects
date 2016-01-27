package cc.cmu.edu.minisite.exception;

public class CustomException extends Exception {
	private String message;
	
	public CustomException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
