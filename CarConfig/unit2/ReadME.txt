Project 1 - Unit 2 - PartB
Student Name: Tianqi Wen
Andrew ID: tianqiw

JRE Version: 1.7
Export using Eclipse

Project Folder: PartB/CarConfig_PartB
Input/Output File directory: CarConfig/Resources
Input text file name: 
configuration.txt       # valid input
dup_opset.txt           # test 1: Model1 with duplicate optionset name
incomplete_opset.txt    # test 2: Model2 with incomplete option set
missing_option.txt      # test 3: Model3 with missing option
missing_price.txt       # test 4: Model4 with missing option price
undeclared_opset.txt    # test 5: Model5 with undeclared option set

Log file:
logs.txt
Log Format:
TimeStamp   ExceptionName  Errno: Erro Message
Wed Sep 23 01:47:46 EDT 2015	DuplicateOpsetException 6: The OptionSet already exists.

Custom Exception:
excepton.AutoException
3 Exceptions handled:
Util.Java: CustomException.UNDECLARED_OPTIONSET
Util.Java: CustomException.MISSING_OPSET
OptionSet.Java: CustomException.MISSING_PRICE
All handled by FixSimple.java

2 Exception fixed
Util.java: CustomException.DUP_OPSET by FixDupOpset.java
Util.java: CustomException.MISSING_OPTION by FixMissingOption.java

Interface:
public interface CreateAuto {
	public void buildAuto(String filename);

	public void printAuto(String modelname);
}

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String OptionSetname,
			String newName);

	public void updateOptionPrice(String Modelname, String Optionname,
			String Option, float newprice);
}

public interface FixAuto {
	public void fix(AutoException ae, String modelName);
}

public interface ChooseAuto {
	public void chooseOption(String modelName, String opsetName, String optName);

	public void printAllChoices(String modelName);

	public void printChoice(String modelName, String opsetName);
	
	public int getTotalPrice(String modelName);
	
	public int getChoicePrice(String modelName, String opsetName);
}


Collection:
LinkedHashMap:
ProxyAutomobile.java
	private LinkedHashMap<String, Automobile> autos
ArrayList:
Automobile.java
    private ArrayList<OptionSet> opsets
	private ArrayList<Option> choice
    private ArrayList<Option> opts