package cc.cmu.edu.minisite.database;

public interface AccountHandler {
	public boolean signin(String username, String password);
	public boolean signup(String username, String password); 
}
