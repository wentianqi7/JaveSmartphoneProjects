package com.example.skyforlife.projectwithactivity.dblayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;

import com.example.skyforlife.projectwithactivity.model.ProfileRecord;
import com.example.skyforlife.projectwithactivity.model.SongRecord;
import com.example.skyforlife.projectwithactivity.utils.Logger;

import java.sql.SQLException;


/**
 * Created by STuotuo.Wen on 2015/11/12.
 */
public class DBAdapter implements SongCRUD, ProfileCRUD, Constants {
    private SQLiteDatabase db;
    private DBOpenHelper dbConn;

    public DBAdapter(Context context) {
        dbConn = new DBOpenHelper(context, DATABASE, null, 1);
    }

    /**
     * open db connection
     *
     * @throws SQLException
     */
    public void start() throws SQLException {
        db = dbConn.getWritableDatabase();
    }

    /**
     * close db connection
     */
    public void close() {
        if (db != null)
            db.close();
    }

    public void createSong(SongRecord sr) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }

        ContentValues tempSr = new ContentValues();
        tempSr.put("sid", Integer.toString(sr.getSid()));
        tempSr.put("name", sr.getName());
        tempSr.put("singer", sr.getSinger());
        tempSr.put("cover", sr.getCover());
        tempSr.put("genre", sr.getGenre());
        tempSr.put("address", sr.getAddress());
        tempSr.put("length", Integer.toString(sr.getLength()));

        db.insert("songs", null, tempSr);
        Logger.log("Create song " + sr.getSid(), 0);
        close();
    }

    public SongRecord readSong(int sid) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        SongRecord sr = null;
        Cursor cursor = db.query("songs", null, "sid=" + sid, null, null, null, null);
        if (cursor.moveToNext()) {
            sr = new SongRecord(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6));
        }
        close();
        return sr;
    }

    public List<SongRecord> getAllSongs() {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        List<SongRecord> songRecordList = new ArrayList<SongRecord>();
        Cursor cursor = db.query("songs", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            songRecordList.add(new SongRecord(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6)));
        }
        close();
        return songRecordList;
    }

    public void updateSong(int sid, SongRecord newRecord) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        close();
    }

    public void deleteSong(int sid) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        int result = db.delete("songs", "sid=" + sid, null);
        Logger.log("delete " + sid + ", " + result, 0);
        close();
    }

    public void createProfile(ProfileRecord profile) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }

        ContentValues tempPr = new ContentValues();
        tempPr.put("username", profile.getUsername());
        tempPr.put("location", profile.getLocation());
        tempPr.put("email", profile.getEmail());
        tempPr.put("image", profile.getImage());

        db.insert("profile", null, tempPr);
        Logger.log("Create profile " + profile.getUsername(), 0);
        close();
    }

    public ProfileRecord readProfile() {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        ProfileRecord profile = null;
        Cursor cursor = db.query("profile", null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            profile = new ProfileRecord(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
        }
        close();
        if (profile != null) {
            Logger.log("read success: " + profile.getUsername(), 0);
        }
        return profile;
    }

    public void updateProfile(String username, ProfileRecord profile) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        close();
    }

    public void deleteProfile(String username) {
        try {
            start();
        } catch (SQLException e) {
            Logger.log(e.toString(), 1);
        }
        int result = db.delete("profile", "username=\'" + username + "\'", null);
        Logger.log("delete " + username + ", " + result, 0);
        close();
    }
}
