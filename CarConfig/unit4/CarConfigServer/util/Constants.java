package util;

public interface Constants {
	public enum FileType {
		PROPERTIES, TXT, MESSAGE
	}

	public String[] TXT_FILE = { "configuration.txt",
			"configuration2.txt" };
	public String[] PROPS_FILE = { "Ford.props", "Test.props" };
	public String[] MODEL_NAME = { "Focus Wagon ZTW", "Test Model" };
	public String[] OPTIONSET = { "Color", "Transmission",
			"Brakes/Traction Control", "Side Impact Air Bags", "Power Moonroof" };
	public String SHOW_MODEL = "show model";
	public String SUCCESS = "success";
	public String FAILED = "failed";
	public String CLOSE = "close";
}
