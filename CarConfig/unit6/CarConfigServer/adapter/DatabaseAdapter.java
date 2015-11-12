package adapter;

import java.sql.SQLException;

import database.AutoDB;
import database.ImplDB;
import model.Automobile;

/**
 * provide access to methods in database package through an API
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class DatabaseAdapter implements AutoDB {
	private ImplDB imp;

	public DatabaseAdapter() {
		imp = new ImplDB();
	}

	public void dbCreate(String filename) {
		imp.dbCreate(filename);
	}

	public void dbDrop(String filename) {
		imp.dbDrop(filename);
	}

	public void dbAddAuto(Automobile auto) {
		imp.dbAddAuto(auto);
	}

	public void dbDeleteAuto(String model) {
		imp.dbDeleteAuto(model);
	}

	public void dbUpdateAutoOpset(String model, String opset, String newName) {
		imp.dbUpdateAutoOpset(model, opset, newName);
	}

	public void dbUpdateAutoPrice(String model, String opset, String option,
			float price) {
		imp.dbUpdateAutoPrice(model, opset, option, price);
	}

	public void dbShowAutoInfo(String model) throws SQLException {
		imp.dbShowAutoInfo(model);
	}
}
