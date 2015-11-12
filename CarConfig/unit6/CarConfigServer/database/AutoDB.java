package database;

import java.sql.SQLException;

import model.Automobile;

public interface AutoDB {
	public void dbCreate(String filename);
	
	public void dbDrop(String filename);

	public void dbAddAuto(Automobile auto);

	public void dbDeleteAuto(String model);

	public void dbUpdateAutoOpset(String model, String opset, String newName);

	public void dbUpdateAutoPrice(String model, String opset, String option, float price);

	public void dbShowAutoInfo(String model) throws SQLException;
}
