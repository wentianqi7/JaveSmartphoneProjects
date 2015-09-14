package model;

/**
 * @author Tianqi Wen (tianqiw) 
 * A Student should has its 4-digit SID and scores
 *         of all the courses
 */
public class Student implements Printable {
	static final String HEAD = "Stud\t\tQ1\tQ2\tQ3\tQ4\tQ5";
	private int SID;
	private int scores[] = new int[NUM_COURSE];

	public Student() {
		SID = 1000;
	}

	public Student(int SID) {
		this.SID = SID;
	}

	// getters and setters

	public int getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int scores[]) {
		this.scores = scores;
	}

	/**
	 * Print a headline to show the contents in each column
	 */
	public void printHeads() {
		StringBuilder sb = new StringBuilder();
		sb.append(HEAD);
		System.out.println(sb.toString());
	}

	/**
	 * Print the scores for all courses of this student
	 */
	public void printInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(SID).append(TAB);
		for (int score : scores) {
			sb.append(TAB).append(score);
		}
		System.out.println(sb.toString());
	}
}
