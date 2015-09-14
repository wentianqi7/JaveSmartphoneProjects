package util;

import model.Statistics;
import model.Student;

/**
 * @author Tianqi Wen (tianqiw) 
 * Define the requirements for Simulation
 */
public abstract class SimulationDef {
	static final int DEFAULT_MAX_SIZE = 40;
	protected Student[] students;

	public SimulationDef() {
		students = new Student[DEFAULT_MAX_SIZE];
	}
	
	public SimulationDef(int maxSize) {
		students = new Student[maxSize];
	}

	/**
	 * print properties of each student in students
	 * 
	 * @param stuCount
	 */
	protected void printStudentInfo(int stuCount) {
		students[0].printHeads();
		for (int i = 0; i < stuCount; i++) {
			students[i].printInfo();
		}
	}

	/**
	 * calculate and print statistical analysis
	 * 
	 * @param stst
	 * @param students
	 */
	protected abstract void printStatistics(Statistics stst, Student[] students);
}
