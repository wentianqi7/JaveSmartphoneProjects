package model;

/**
 * @author Tianqi Wen (tianqiw) 
 * A Statistics should be able to find the low/high/average score
 */
public interface Statistics {
	static final String HIGH = "High Score";
	static final String LOW = "\nLow Score";
	static final String AVG = "\nAverage\t";

	public void findlow(Student[] students);

	public void findhigh(Student[] students);

	public void findavg(Student[] students);
}