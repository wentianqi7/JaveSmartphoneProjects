package database;

import java.sql.*;
import util.Util;

/**
 * connect to and query the database using JDBC
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class JDBCAdapter {
	private final String SQL_FILE = "query.txt";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private String url;
	private String username;
	private String password;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private Util util = new Util();

	public JDBCAdapter(String url, String uname, String pwd) {
		this.url = url;
		this.username = uname;
		this.password = pwd;
		this.createConn();
	}

	public void createConn() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			stmt.execute(util.readSql(SQL_FILE, "CREATE_DATABASE"));
			conn.setCatalog("CAR_CONFIG");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void dropTables() {
		if (conn == null) {
			return;
		}
		this.executeWriteQuery(util.readSql(SQL_FILE, "DROP_OPTIONS"));
		this.executeWriteQuery(util.readSql(SQL_FILE, "DROP_OPSET"));
		this.executeWriteQuery(util.readSql(SQL_FILE, "DROP_MODELS"));
	}

	public void createTables() {
		this.executeWriteQuery(util.readSql(SQL_FILE, "CREATE_MODEL"));
		this.executeWriteQuery(util.readSql(SQL_FILE, "CREATE_OPSET"));
		this.executeWriteQuery(util.readSql(SQL_FILE, "CREATE_OPTION"));
		System.out.println("Table create success.");
	}

	public boolean executeWriteQuery(String query) {
		if (conn == null || stmt == null) {
			return false;
		}
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ResultSet executeReadQuery(String query) {
		if (conn == null || stmt == null) {
			return null;
		}
		try {
			rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void closeConn() throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}

	public void showModelDB(String model) throws SQLException {
		StringBuilder sb = new StringBuilder();
		rs = this.executeReadQuery(String.format(
				util.readSql(SQL_FILE, "SELECT_MODEL"), model));
		sb.append("-----------Models------------\n");
		if (rs != null && rs.next()) {
			String modelName = rs.getString(1);
			String make = rs.getString(2);
			float basePrice = rs.getFloat(3);
			sb.append("Model: ").append(modelName).append("\n");
			sb.append("Make: ").append(make).append("\n");
			sb.append("Base price: ").append(basePrice).append("\n");
		} else {
			System.out.println("Model does not exist " + model);
		}
		sb.append("-------------OptionSet------------\n");
		rs = this.executeReadQuery(String.format(
				util.readSql(SQL_FILE, "SELECT_OPTIONS"), model));
		String prevName = null;
		while (rs != null && rs.next()) {
			String opsetName = rs.getString(1);
			if (prevName == null || !opsetName.equals(prevName)) {
				sb.append(opsetName).append(":\n");
				prevName = opsetName;
			}
			String optionName = rs.getString(2);
			float price = rs.getFloat(3);
			sb.append("\t").append(optionName).append(": ").append(price)
					.append("\n");
		}

		System.out.println(sb.toString());
	}

	protected void finalize() throws Throwable {
		this.closeConn();
		super.finalize();
	}
}
