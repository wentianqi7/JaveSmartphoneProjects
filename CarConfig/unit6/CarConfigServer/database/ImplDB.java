package database;

import java.sql.SQLException;
import util.Util;
import model.Automobile;

/**
 * implement the methods declared in AutoDB
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class ImplDB implements AutoDB {
	private final String SQL_FILE = "query.txt";
	private final String URL = "jdbc:mysql://localhost:3306/";
	private final String USERNAME = "root";
	private final String PASSWORD = "1234";
	private JDBCAdapter adapter;
	private Util util;

	public ImplDB() {
		adapter = new JDBCAdapter(URL, USERNAME, PASSWORD);
		util = new Util();
	}

	public void dbCreate(String filename) {
		adapter.createTables();
	}

	public void dbDrop(String filename) {
		adapter.dropTables();
	}

	public void dbAddAuto(Automobile auto) {
		auto.writeAutoToDB(adapter);
	}

	public void dbDeleteAuto(String model) {
		adapter.executeWriteQuery(String.format(
				util.readSql(SQL_FILE, "DELETE_OPTION"), model));
		adapter.executeWriteQuery(String.format(
				util.readSql(SQL_FILE, "DELETE_OPSET"), model));
		adapter.executeWriteQuery(String.format(
				util.readSql(SQL_FILE, "DELETE_MODEL"), model));
	}

	public void dbUpdateAutoOpset(String model, String opset, String newName) {
		adapter.executeWriteQuery(String.format(
				util.readSql(SQL_FILE, "UPDATE_NAME"), newName, model, opset));
	}

	public void dbUpdateAutoPrice(String model, String opset, String option,
			float price) {
		adapter.executeWriteQuery(String.format(
				util.readSql(SQL_FILE, "UPDATE_PRICE"), price, option, model,
				opset));
	}

	public void dbShowAutoInfo(String model) throws SQLException {
		adapter.showModelDB(model);
	}
}
