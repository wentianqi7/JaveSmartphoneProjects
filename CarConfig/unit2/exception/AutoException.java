package exception;

import model.Automobile;

/**
 * Customer Exception and fix method
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class AutoException extends Exception {

	private static final long serialVersionUID = -2761321396076197557L;
	private CustomException cusExcp;
	private Object[] inputs;

	public AutoException() {
		super();
	}

	public AutoException(int errno) {
		cusExcp = CustomException.values()[errno];
	}

	public AutoException(CustomException cusExcp, Object... inputs) {
		this.cusExcp = cusExcp;
		this.inputs = inputs;
	}

	public void fix(Automobile auto) {
		switch (cusExcp) {
		case MISSING_PRICE:
		case MISSING_OPSET:
		case UNDECLARED_OPTIONSET:
		case INVALID_HEAD_FORMAT:
		case MODEL_NOT_FOUND:
			// handle
			FixSimple simple = new FixSimple();
			simple.fix(cusExcp.toString());
			break;
		case MISSING_OPTION:
			// fix by adding default option
			FixMissingOption fm = new FixMissingOption();
			fm.fix(auto, (int) inputs[0], (String) inputs[1],
					cusExcp.toString());
			break;
		case DUP_OPSET:
			// fix by rename the duplicant
			FixDupOpset fd = new FixDupOpset();
			fd.fix(auto, (String) inputs[0], (String[]) inputs[1],
					cusExcp.toString());
			break;
		}
	}

	@Override
	public String getMessage() {
		return cusExcp.getMessage();
	}

	public int getErrno() {
		return cusExcp.getCode();
	}

	@Override
	public String toString() {
		return cusExcp.toString();
	}
}