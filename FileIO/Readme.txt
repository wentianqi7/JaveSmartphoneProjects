Assignment 2
Student Name: Tianqi Wen
Andrew ID: tianqiw

JRE Version: 1.7
Export using Eclipse

Project Folder: FileIO
To run the simulation, execute main method under Simulation.java in util package

Interface: Printable, Statistics
Abstract class: SimulationDef
File IO API: Util.java
Custom Exception: RecordOversizeException
    Handler: Util.java

Diagram File: FileIO_Diagram.pdf
    
Packages:
1. util
SimulationDef.java
    public SimulationDef(int maxSize)
    protected void printStudentInfo(int stuCount)
    protected abstract void printStatistics(Statistics stst, Student[] students)
    
Simulation.java:
    class Simulation extends SimulationDef
    Contains main method
    test cases (total 8)
    maximum class size: 40
    Test 1: 0 student record with headline (Stud Qu1 Qu2 Qu3 Qu4 Qu5) | test1.txt
	Test 2: 0 student record without headline (empty file)            | test2.txt
	Test 3: 15 student records                                        | test3.txt
    Test 4: 40 student records                                        | test4.txt
	Test 5: 41 student records                                        | test5.txt
    Test 6: 80 student records                                        | test6.txt
	Test 7: File not found                                            | test7.txt
    Test 8: Invalid record format                                     | test8.txt
    
Util.java
    Reads the file and builds student array.
    public static int readFile(String filename, Student[] students)
    
2. model
Printable.java
    public void printHeads();
	public void printInfo();

Statistics.java
    Declare statistical analysis methods
    public void findlow(Student[] students);
	public void findhigh(Student[] students);
	public void findavg(Student[] students);
    
StatisticsImp.java
    Implementation of statistical analysis and print the result
    class StatisticsImp implements Printable, Statistics
    
Student.java
    Store and print scores of this student
    class Student implements Printable
    private int SID;
	private int scores[]
    public void printHeads();
	public void printInfo();
    
3. exception
RecordOversizeException.java
    class RecordOversizeException extends Exception
    Throwed when the student record size is greater than maximum size (40 in this simulation)