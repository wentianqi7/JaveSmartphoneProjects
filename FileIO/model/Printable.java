package model;

/**
 * @author Tianqi Wen (tianqiw) 
 * A Printable should be able to print a header and
 *         the properites in that class
 */
public interface Printable {
	static final int NUM_COURSE = 5;
	static final String TAB = "\t";

	public void printHeads();

	public void printInfo();
}
