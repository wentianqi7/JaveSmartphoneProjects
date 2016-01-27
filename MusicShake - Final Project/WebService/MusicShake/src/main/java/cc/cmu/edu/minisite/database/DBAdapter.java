package cc.cmu.edu.minisite.database;

import java.sql.*;

import cc.cmu.edu.minisite.model.SongRecord;
import cc.cmu.edu.minisite.util.Logger;

import java.util.*;

public class DBAdapter implements SongHandler, AccountHandler, DBConstants {
	JDBCAdapter adapter = null;

	public DBAdapter() {
		adapter = new JDBCAdapter();
	}

	public boolean isAuth(String username, String password) {

		return false;
	}

	@Override
	public List<SongRecord> searchSongRecord(String pattern) {
		if (adapter == null) {
			return null;
		}
		adapter.createConn();
		List<SongRecord> srList = new LinkedList<SongRecord>();
		try {
			ResultSet rs = adapter.executeReadQuery(String.format(
					SELECT_SONG_URL, pattern, pattern));
			while (rs.next()) {
				SongRecord sr = new SongRecord(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
				srList.add(sr);
			}
			adapter.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return srList;
	}

	@Override
	public SongRecord getSongRecord(int sid) {
		if (adapter == null) {
			return null;
		}
		adapter.createConn();
		SongRecord sr = null;
		try {
			ResultSet rs = adapter.executeReadQuery(String.format(SONG_BY_ID,
					sid));
			if (rs.next()) {
				sr = new SongRecord(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
			}
			adapter.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sr;
	}

	@Override
	public boolean signin(String username, String password) {
		if (adapter == null) {
			return false;
		}
		adapter.createConn();
		boolean success = false;
		try {
			ResultSet rs = adapter.executeReadQuery(String.format(SIGNIN_QUERY,
					username, password));
			success = rs.next();
			adapter.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean signup(String username, String password) {
		if (adapter == null) {
			return false;
		}
		adapter.createConn();
		boolean success = false;
		try {
			ResultSet rs = adapter.executeReadQuery(String.format(
					CHECK_EXIST_QUERY, username));
			success = !rs.next();
			if (success) {
				success = adapter.executeWriteQuery(String.format(SIGNUP_QUERY,
						username, password));
			}
			adapter.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}