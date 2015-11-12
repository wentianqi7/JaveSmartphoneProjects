package adapter;

/**
 * interface for create an automobile
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public interface CreateAuto {
	public void buildAuto(String filename, String fileType);

	public void printAuto(String modelname);
	
	public void printAllAutos();
}
