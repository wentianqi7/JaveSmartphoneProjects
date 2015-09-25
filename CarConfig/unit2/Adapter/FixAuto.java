package Adapter;

import exception.AutoException;

/**
 * interface for fix customized exceptions
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface FixAuto {
	public void fix(AutoException ae, String modelName);
}
