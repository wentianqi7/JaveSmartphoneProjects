package exception;

/**
 * enum class which define errno, exception name and exception description
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public enum CustomException {
	MISSING_PRICE(0, "MissingPriceException",
					"The price for Automobile is missing in text file."), 
			MISSING_OPSET(1, "MissingOpsetDataException", 
					"OptionSet data is not complete."), 
			MISSING_OPTION(2, "MissingOptionDataException", 
					"Option data is not complete."), 
			UNDECLARED_OPTIONSET(3, "UndeclaredOpsetException", 
					"The OptionSet is not declared."), 
			INVALID_HEAD_FORMAT(4, "InvalidHeadFormatException",
					"The input text file header format is not valid."), 
			MODEL_NOT_FOUND(5, "ModelNotFoundException", 
					"The model name cannot be found."), 
			DUP_OPSET(6, "DuplicateOpsetException", 
					"The OptionSet already exists.");

	private final int code;
	private final String name;
	private final String message;

	private CustomException(int code, String name, String message) {
		this.code = code;
		this.name = name;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(code).append(": ").append(message);
		return sb.toString();
	}
}
