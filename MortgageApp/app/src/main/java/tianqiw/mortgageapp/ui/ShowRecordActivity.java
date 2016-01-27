package tianqiw.mortgageapp.ui;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.*;
import tianqiw.mortgageapp.util.*;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 *
 * this class is used to show all records in the db
 */
public class ShowRecordActivity extends AppCompatActivity {
    private Spinner allRecordsSpinner;
    private DBHelper db = new DBHelper(ShowRecordActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);

        // get all records
        List<String> dbRecords = null;
        try {
            dbRecords = getRecordFromDB();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }

        ArrayAdapter<String> recordAdapter = new ArrayAdapter<String>(ShowRecordActivity.this,
                android.R.layout.simple_spinner_item, dbRecords);

        allRecordsSpinner = (Spinner) findViewById(R.id.records);
        allRecordsSpinner.setAdapter(recordAdapter);
    }

    private List<String> getRecordFromDB() throws SQLException {
        List<String> record = new LinkedList<String>();
        db.start();
        Cursor cursor = db.getAll();

        while(cursor.moveToNext()){
            StringBuilder sb = new StringBuilder();
            sb.append("Id:").append(cursor.getInt(0))
                    .append(",monthlyTotal:").append(cursor.getString(2))
                    .append(",totalPayment:").append(cursor.getString(3))
                    .append(",payOffDate:").append(cursor.getString(4))
                    .append("/").append(cursor.getString(5));
            record.add(sb.toString());
        }
        db.close();
        return record;
    }
}
