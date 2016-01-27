package cc.cmu.edu.minisite.database;

import java.sql.*;

import cc.cmu.edu.minisite.util.Logger;

/**
 * connect to and query the database using JDBC
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class JDBCAdapter implements DBConstants {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public void createConn() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	protected void finalize() throws Throwable {
		this.closeConn();
		super.finalize();
	}
}
