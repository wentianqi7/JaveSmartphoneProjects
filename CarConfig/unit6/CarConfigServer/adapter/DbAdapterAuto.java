package adapter;

/*
 * interface for interact with db
 */
public interface DbAdapterAuto {
	public void initDB(String createFilename, String dropFilename);

	public void showModelDB(String model);
}
