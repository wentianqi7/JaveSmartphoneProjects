package util;

import model.Statistics;
import model.StatisticsImp;
import model.Student;

/**
 * @author Tianqi Wen (tianqiw) 
 * A simulation program to perform statistical
 *         analysis of scores for a class of students 
 * To make a simulation,
 *         start a new test case and call printStudentInfo and printStatistics
 *         to see the result
 */
public class Simulation extends SimulationDef {
	private static final String[] TEST = { "test1.txt", "test2.txt",
			"test3.txt", "test4.txt", "test5.txt", "test6.txt", "test7.txt",
			"test8.txt" };
	private static final String[] TEST_MESSAGE = {
			"Test 1: 0 student record with headline (Stud Qu1 Qu2 Qu3 Qu4 Qu5)",
			"Test 2: 0 student record without headline (empty file)",
			"Test 3: 15 student records", "Test 4: 40 student records",
			"Test 5: 41 student records", "Test 6: 80 student records",
			"Test 7: File not found", "Test 8: Invalid record format" };
	private static final String TEST_LINE = "\n-------------\n";
	private static final int NUM_TEST = 8;
	private int stuCount;
	private Statistics st;

	public Simulation() {
		stuCount = 0;
		st = null;
	}

	public static void main(String[] args) {

		Simulation sim = new Simulation();

		for (int i = 0; i < NUM_TEST; i++) {
			sim.runSingleTest(i);
		}
	}

	/**
	 * Run a single test on the ith testing file
	 * 
	 * @param testID
	 */
	private void runSingleTest(int testID) {
		stuCount = Util.readFile(TEST[testID], students);
		st = new StatisticsImp(stuCount);
		System.out.println(String.format(TEST_MESSAGE[testID]));
		// print student records
		printStudentInfo(stuCount);
		// print statistical analysis of scores for the class
		printStatistics(st, students);
		System.out.println(TEST_LINE);
	}

	/**
	 * calculate and print statistical analysis of scores for the class
	 * 
	 * @param st
	 * @param students
	 */
	protected void printStatistics(Statistics st, Student[] students) {
		st.findhigh(students);
		st.findlow(students);
		st.findavg(students);
		((StatisticsImp) st).printHeads();
		((StatisticsImp) st).printInfo();
	}
}
