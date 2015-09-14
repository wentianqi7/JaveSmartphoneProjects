package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import exception.RecordOversizeException;
import model.Student;

/**
 * @author Tianqi Wen (tianqiw) 
 * Implement util methods including read student records from file
 */
public class Util {
	static final int NUM_COURSE = 5;
	static final String PATH = "Resources/%s";
	static final String NOT_FOUND = "ERROR: file not found: %s !!!";
	static final String NUM_FORMAT = "ERROR: line format corruption: %s";
	static final String MESSAGE = "Warning: Record size is greater than max size: %d !!!";

	/**
	 * Reads the file and builds student array.
	 * 
	 * @param filename
	 * @param students
	 * @return number of student records
	 */
	public static int readFile(String filename, Student[] students) {

		int stuCount = 0;
		students[0] = new Student();

		// Open the file using FileReader Object.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					String.format(PATH, filename)));
			String line = br.readLine();

			// In a loop read a line using readLine method.
			while ((line = br.readLine()) != null) {

				if (stuCount >= students.length) {
					// throw custom exception if records size is greater than
					// limit
					throw new RecordOversizeException(String.format(MESSAGE,
							students.length));
				}

				try {
					int SID = 0;
					int qid = 0;
					// Tokenize each line using StringTokenizer Object
					StringTokenizer st = new StringTokenizer(line);
					int[] scores = new int[NUM_COURSE];

					if (st.hasMoreTokens()) {
						// Each token is converted from String to Integer using
						// parseInt method
						SID = Integer.parseInt(st.nextToken());
					}
					Student tmpStudent = new Student(SID);

					while (st.hasMoreTokens()) {
						scores[qid++] = Integer.parseInt(st.nextToken());
					}
					// Value is then saved in the right property of Student
					// Object.
					tmpStudent.setScores(scores);
					students[stuCount++] = tmpStudent;
				} catch (NumberFormatException nfe) {
					System.err.println(String.format(NUM_FORMAT, line));
					continue;
				}
			}
			br.close();
		} catch (FileNotFoundException fne) {
			System.err.println(String.format(NOT_FOUND, filename));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (RecordOversizeException roe) {
			System.err.println(roe.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return stuCount;
	}
}
