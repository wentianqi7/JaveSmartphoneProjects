package tianqiw.scorestatistic.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.*;

import tianqiw.scorestatistic.model.StudentRecord;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * helps dealing database issues
 */
public class DBAdapter implements Constants {
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

    public StudentRecord getStudentRecord(int sid) {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        Cursor cursor = db.query("records", null, "sid="+ sid, null, null, null, null);

        if (cursor.moveToNext()) {
            List<Double> scores = new ArrayList<Double>();
            for (int i = 1; i < 6; i++) {
                scores.add(cursor.getDouble(i));
            }
            close();
            return new StudentRecord(cursor.getInt(0), scores);
        } else {
            close();
            return null;
        }
    }

    public boolean isFull() {
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        Cursor cursor = db.query("records", null, null, null, null, null, null);
        int count = 0;
        while (cursor.moveToNext())  {
            count++;
        }
        close();
        return count >= 40;
    }

    public List<StudentRecord> getAllRecords() {
        List<StudentRecord> studentList = new ArrayList<StudentRecord>();
        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        Cursor cursor = db.query("records", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            List<Double> scores = new ArrayList<Double>();
            for (int i = 1; i < 6; i++) {
                scores.add(cursor.getDouble(i));
            }
            studentList.add(new StudentRecord(cursor.getInt(0), scores));
        }
        close();

        return studentList;
    }

    public void insertRecord(StudentRecord sr) {
        ContentValues tempSr = new ContentValues();
        tempSr.put(FIELDS[0], Integer.toString(sr.getSid()));
        for (int i = 0; i < 5; i++) {
            tempSr.put(FIELDS[i + 1], Double.toString(sr.getScore(i)));
        }

        try {
            start();
        } catch (SQLException e) {
            System.err.println(e);
        }
        db.insert("records", null, tempSr);
        close();
    }
}
