package tianqiw.mortgageapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

import tianqiw.mortgageapp.model.*;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 *
 * this class is used to interact with the SQLite database
 */
public class DBHelper implements Constants {
    private SQLiteDatabase db;
    private DBOpenHelper dbConn;

    public DBHelper(Context context) {
        dbConn = new DBOpenHelper(context, DATABASE, null, 1);
    }

    /**
     * open db connection
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

    /**
     * get all records from db
     * @return all mortgage records
     */
    public Cursor getAll() {
        return db.query("myMortgage", null, null, null, null, null, "timestamp");
    }

    /**
     * insert a record into db
     * @param model the model we want to insert
     */
    public void insert(Model model) {
        ContentValues tempModel = new ContentValues();
        tempModel.put(FIELDS[0], Integer.toString(model.getPurchasePrice()));
        tempModel.put(FIELDS[1], Integer.toString(model.getDownPayment()));
        tempModel.put(FIELDS[2], Integer.toString(model.getMortgageTerm()));
        tempModel.put(FIELDS[3], Double.toString(model.getInterestRate()));
        tempModel.put(FIELDS[4], Integer.toString(model.getFirstPaymentMonth()));
        tempModel.put(FIELDS[5], Integer.toString(model.getFirstPaymentYear()));
        tempModel.put(FIELDS[6], Double.toString(model.getTotalMonthPay()));
        tempModel.put(FIELDS[7], Double.toString(model.getTotalPayment()));
        tempModel.put(FIELDS[8], Integer.toString(model.getPayOffMonth()));
        tempModel.put(FIELDS[9], Integer.toString(model.getPayOffYear()));

        try {
            start();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        db.insert("myMortgage", null, tempModel);
        close();
    }


}
