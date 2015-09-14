package model;

/**
 * @author Tianqi Wen (tianqiw) 
 * Find the statistical values and print the result
 */
public class StatisticsImp implements Statistics, Printable {
	private int[] lowscores = new int[NUM_COURSE];
	private int[] highscores = new int[NUM_COURSE];
	private float[] avgscores = new float[NUM_COURSE];
	private int stuCount;

	public StatisticsImp() {
		stuCount = 0;
	}

	public StatisticsImp(int stuCount) {
		this.stuCount = stuCount;
	}

	/**
	 * This method will find the lowest score and store it in an array names
	 * lowscores.
	 */
	public void findlow(Student[] students) {

		for (int i = 0; i < NUM_COURSE; i++) {
			// find the lowest score for the ith course
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < stuCount; j++) {
				Student stu = students[j];
				if (stu.getScores()[i] < min) {
					min = stu.getScores()[i];
				}
			}
			this.lowscores[i] = min;
		}
	}

	/**
	 * This method will find the highest score and store it in an array names
	 * highscores.
	 */
	public void findhigh(Student[] students) {
		for (int i = 0; i < NUM_COURSE; i++) {
			// find the highest score for the ith course
			for (int j = 0; j < stuCount; j++) {
				this.highscores[i] = Math.max(students[j].getScores()[i],
						this.highscores[i]);
			}
		}
	}

	/**
	 * This method will find avg score for each quiz and store it in an array
	 * names avgscores.
	 */
	public void findavg(Student[] students) {

		for (int i = 0; i < NUM_COURSE; i++) {
			// find the total score for the ith course
			int total = 0;
			for (int j = 0; j < stuCount; j++) {
				total += students[j].getScores()[i];
			}
			// calculate the average score by dividing number of students
			if (stuCount == 0) {
				this.avgscores[i] = 0;
			} else {
				this.avgscores[i] = total / (float) stuCount;
			}

		}
	}

	/**
	 * Print the headline
	 */
	public void printHeads() {
		System.out.println();
	}

	/**
	 * Print statistical analysis, including low/high/average score
	 */
	public void printInfo() {
		StringBuilder sb = new StringBuilder();
		
		if (stuCount < 1) {
			return;
		}
		
		sb.append(HIGH);
		for (int score : this.highscores) {
			sb.append(TAB).append(score);
		}
		sb.append(LOW);
		for (int score : this.lowscores) {
			sb.append(TAB).append(score);
		}
		sb.append(AVG);
		for (float score : this.avgscores) {
			sb.append(TAB).append(String.format("%.1f", score));
		}

		System.out.println(sb.toString());
	}
}
