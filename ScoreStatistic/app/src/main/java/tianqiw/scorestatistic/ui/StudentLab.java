package tianqiw.scorestatistic.ui;

import android.content.Context;
import java.util.*;
import tianqiw.scorestatistic.model.StudentRecord;
import tianqiw.scorestatistic.util.DBAdapter;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * interact with the database manipulations
 */
public class StudentLab {
    private static StudentLab studentLab;
    private Context appContext;
    private static DBAdapter adapter;

    private StudentLab(Context appContext) {
        this.appContext = appContext;
        adapter = new DBAdapter(appContext);
    }

    public static StudentLab get(Context c) {
        if (studentLab == null) {
            studentLab = new StudentLab(c.getApplicationContext());
        }
        return studentLab;
    }

    public StudentRecord getStudentRecord(int sid) {
        return adapter.getStudentRecord(sid);
    }

    public List<StudentRecord> getAllRecords() {
        return adapter.getAllRecords();
    }

    public void insertRecord(StudentRecord sr) {
        adapter.insertRecord(sr);
    }

    public boolean isFull() {
        return adapter.isFull();
    }
}
